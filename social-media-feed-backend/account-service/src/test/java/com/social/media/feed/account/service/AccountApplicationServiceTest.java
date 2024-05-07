package com.social.media.feed.account.service;

import com.social.media.feed.account.service.application.dto.AccountDetails;
import com.social.media.feed.account.service.application.port.service.AuthenticationService;
import com.social.media.feed.account.service.application.util.JwtTokenUtil;
import com.social.media.feed.account.service.domain.entity.Account;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountApplicationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void testAuthentication() {
        final Account account = Account.builder()
                .accountId(UUID.randomUUID())
                .username("test")
                .password("test")
                .build();
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        when(userDetailsService.loadUserByUsername(account.getUsername())).thenReturn(
                new AccountDetails(account.getId().getValue(), account.getUsername(), encodedPassword));

        String token = authenticationService.authenticateAccount(account);
        Claims claims = jwtTokenUtil.extractAllClaims(token);
        String username = claims.getSubject();
        String accountId = claims.get("accountId", String.class);

        assertEquals(account.getUsername(), username);
        assertEquals(account.getId().getValue().toString(), accountId);
    }
}
