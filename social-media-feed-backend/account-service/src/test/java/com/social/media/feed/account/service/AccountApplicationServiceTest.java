package com.social.media.feed.account.service;

import com.social.media.feed.account.service.application.dto.AccountDetails;
import com.social.media.feed.account.service.application.port.repository.AccountRepository;
import com.social.media.feed.account.service.application.port.service.AuthenticationService;
import com.social.media.feed.account.service.application.rest.controller.AccountController;
import com.social.media.feed.account.service.application.rest.controller.AuthenticateController;
import com.social.media.feed.account.service.application.rest.model.LoginAccountRequestBody;
import com.social.media.feed.account.service.application.rest.model.RegisterAccountRequestBody;
import com.social.media.feed.account.service.application.util.JwtTokenUtil;
import com.social.media.feed.account.service.domain.entity.Account;
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
    public void testAuthentication()  {
        final String username = "usertest";

        // Test account registration
        RegisterAccountRequestBody registerAccountRequestBody = new RegisterAccountRequestBody(username, "test");
        ResponseEntity<String> response = accountController.registerAccount(registerAccountRequestBody);

        assertEquals(ResponseEntity.class, response.getClass());

        // Test authentication
        LoginAccountRequestBody loginAccountRequestBody = new LoginAccountRequestBody(username, "test");
        String token = authenticateController.authenticate(loginAccountRequestBody);

        try {
            LoginAccountRequestBody invalidCredentials = new LoginAccountRequestBody(username, "test1");
            authenticateController.authenticate(invalidCredentials);
        } catch (Exception e) {
            assertEquals("Bad credentials", e.getMessage());
        }

        // Validate token
        assertNotNull(token);
        Claims claims = jwtTokenUtil.extractAllClaims(token);
        assertEquals(username, claims.getSubject());
    }
}
