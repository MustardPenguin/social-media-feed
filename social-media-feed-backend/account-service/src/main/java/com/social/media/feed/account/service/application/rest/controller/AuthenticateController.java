package com.social.media.feed.account.service.application.rest.controller;

import com.social.media.feed.account.service.application.mapper.AccountServiceMapper;
import com.social.media.feed.account.service.application.port.service.AccountService;
import com.social.media.feed.account.service.application.port.service.AuthenticationService;
import com.social.media.feed.account.service.application.rest.model.AuthenticationResponse;
import com.social.media.feed.account.service.application.rest.model.LoginAccountRequestBody;
import com.social.media.feed.account.service.domain.entity.Account;
import com.social.media.feed.application.rest.model.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticateController {

    private final AuthenticationService authenticationService;
    private final AccountServiceMapper accountServiceMapper;

    public AuthenticateController(AuthenticationService authenticationService, AccountServiceMapper accountServiceMapper) {
        this.authenticationService = authenticationService;
        this.accountServiceMapper = accountServiceMapper;
    }

    @PostMapping
    public ResponseEntity<HttpResponse> authenticate(@RequestBody LoginAccountRequestBody loginAccountRequestBody) {
        Account account = accountServiceMapper.loginAccountRequestBodyToAccount(loginAccountRequestBody);
        String token = authenticationService.authenticateAccount(account);
        return ResponseEntity.ok(new AuthenticationResponse("Successfully authenticated!", token));
    }
}
