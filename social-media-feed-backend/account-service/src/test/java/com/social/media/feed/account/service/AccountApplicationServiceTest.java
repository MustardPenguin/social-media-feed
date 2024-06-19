package com.social.media.feed.account.service;

import com.social.media.feed.account.service.application.dto.FollowWithUsername;
import com.social.media.feed.account.service.application.port.repository.AccountRepository;
import com.social.media.feed.account.service.application.rest.controller.AccountController;
import com.social.media.feed.account.service.application.rest.controller.AuthenticateController;
import com.social.media.feed.account.service.application.rest.controller.FollowController;
import com.social.media.feed.account.service.application.rest.model.request.LoginAccountRequestBody;
import com.social.media.feed.account.service.application.rest.model.request.RegisterAccountRequestBody;
import com.social.media.feed.account.service.application.rest.model.response.AuthenticationResponse;
import com.social.media.feed.account.service.application.util.JwtTokenUtil;
import com.social.media.feed.account.service.domain.entity.Account;
import com.social.media.feed.account.service.infrastructure.repository.outbox.followcreated.FollowCreatedEventEntity;
import com.social.media.feed.account.service.infrastructure.repository.outbox.followcreated.FollowCreatedEventJpaRepository;
import com.social.media.feed.application.rest.model.AccountResponse;
import com.social.media.feed.application.rest.model.HttpResponse;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {"eureka.client.enabled=false", "spring.datasource.hikari.max-life = 600000"})
public class AccountApplicationServiceTest extends BaseTest {

    @Autowired
    private AuthenticateController authenticateController;
    @Autowired
    private AccountController accountController;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testAccountService()  {
        final String username = "usertest";

        // Test account registration
        RegisterAccountRequestBody registerAccountRequestBody = new RegisterAccountRequestBody(username, "test");
        ResponseEntity<HttpResponse> response = accountController.registerAccount(registerAccountRequestBody);

        assertEquals(ResponseEntity.class, response.getClass());

        // Test authentication
        LoginAccountRequestBody loginAccountRequestBody = new LoginAccountRequestBody(username, "test");
        ResponseEntity<AuthenticationResponse> tokenResponse = authenticateController.authenticate(loginAccountRequestBody);

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
        assertNotNull(tokenResponse);
        assertNotNull(tokenResponse.getBody());
        Claims claims = jwtTokenUtil.extractAllClaims(tokenResponse.getBody().getToken());
        assertEquals(username, claims.getSubject());
        UUID accountId = UUID.fromString(claims.get("accountId", String.class));
        assertEquals(account.getId().getValue(), accountId);
    }
}
