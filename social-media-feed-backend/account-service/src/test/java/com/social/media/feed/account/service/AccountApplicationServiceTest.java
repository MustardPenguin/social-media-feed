package com.social.media.feed.account.service;

import com.social.media.feed.account.service.application.dto.AccountDetails;
import com.social.media.feed.account.service.application.port.repository.AccountRepository;
import com.social.media.feed.account.service.application.port.service.AuthenticationService;
import com.social.media.feed.account.service.application.port.service.FollowService;
import com.social.media.feed.account.service.application.rest.controller.AccountController;
import com.social.media.feed.account.service.application.rest.controller.AuthenticateController;
import com.social.media.feed.account.service.application.rest.controller.FollowController;
import com.social.media.feed.account.service.application.rest.model.LoginAccountRequestBody;
import com.social.media.feed.account.service.application.rest.model.RegisterAccountRequestBody;
import com.social.media.feed.account.service.application.util.JwtTokenUtil;
import com.social.media.feed.account.service.domain.entity.Account;
import com.social.media.feed.account.service.infrastructure.repository.outbox.followcreated.FollowCreatedEventEntity;
import com.social.media.feed.account.service.infrastructure.repository.outbox.followcreated.FollowCreatedEventJpaRepository;
import com.social.media.feed.application.rest.model.AccountResponse;
import io.jsonwebtoken.Claims;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class AccountApplicationServiceTest {

    @Autowired
    private AuthenticateController authenticateController;
    @Autowired
    private AccountController accountController;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private FollowController followController;
    @Autowired
    private FollowCreatedEventJpaRepository followCreatedEventJpaRepository;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withUrlParam("stringtype", "unspecified")
            .withUrlParam("currentSchema", "account")
            .withInitScript("init-schema.sql")
            .withReuse(true);

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    public static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    public void testAccountService()  {
        final String username = "usertest";

        // Test account registration
        RegisterAccountRequestBody registerAccountRequestBody = new RegisterAccountRequestBody(username, "test");
        ResponseEntity<String> response = accountController.registerAccount(registerAccountRequestBody);

        assertEquals(ResponseEntity.class, response.getClass());

        // Test authentication
        LoginAccountRequestBody loginAccountRequestBody = new LoginAccountRequestBody(username, "test");
        String token = authenticateController.authenticate(loginAccountRequestBody);

        // Login with invalid credentials
        try {
            LoginAccountRequestBody invalidCredentials = new LoginAccountRequestBody(username, "test1");
            authenticateController.authenticate(invalidCredentials);
        } catch (Exception e) {
            assertEquals("Bad credentials", e.getMessage());
        }

        // Register with username already existing
        try {
            RegisterAccountRequestBody sameUsername = new RegisterAccountRequestBody(username, "test");
            accountController.registerAccount(sameUsername);
        } catch (Exception e) {
            assertEquals("Account already exists with username " + username, e.getMessage());
        }


        // Get account
        Account account = accountRepository.findAccountByUsername(username);
        assertNotNull(account);
        assertEquals(username, account.getUsername());

        // Verify account controller get account
        ResponseEntity<AccountResponse> accountResponseEntity = accountController.getAccount(account.getId().getValue());
        AccountResponse accountResponse = accountResponseEntity.getBody();

        assertNotNull(accountResponse);
        assertEquals(account.getId().getValue(), accountResponse.getAccountId());
        assertEquals(account.getUsername(), accountResponse.getUsername());

        ResponseEntity<AccountResponse> nullAccountResponseEntity = accountController.getAccount(UUID.randomUUID());
        assertNull(nullAccountResponseEntity);

        // Validate token
        assertNotNull(token);
        Claims claims = jwtTokenUtil.extractAllClaims(token);
        assertEquals(username, claims.getSubject());
        UUID accountId = UUID.fromString(claims.get("accountId", String.class));
        assertEquals(account.getId().getValue(), accountId);
    }

    @Test
    public void testFollowService() {
        // Create accounts
        accountController.registerAccount(new RegisterAccountRequestBody("account1", "password"));
        accountController.registerAccount(new RegisterAccountRequestBody("account2", "password"));

        // Test get account by id from account service
        Account account1 = accountRepository.findAccountByUsername("account1");
        Account account2 = accountRepository.findAccountByUsername("account2");

        // Test follow service
        ResponseEntity<String> response = followController.follow(account1.getId().getValue(), account2.getId().getValue());

        assertEquals(ResponseEntity.class, response.getClass());

        // Test follow service with already following account
        try {
            followController.follow(account1.getId().getValue(), account2.getId().getValue());
        } catch (Exception e) {
            assertEquals("Already following the account with accountId " + account1.getId().getValue(), e.getMessage());
        }

        // Validate that a follow created event was created
        List<FollowCreatedEventEntity> followCreatedEventEntities = followCreatedEventJpaRepository.findAll();
        assertEquals(1, followCreatedEventEntities.size());
    }
}
