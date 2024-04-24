package com.social.media.feed.account.service.application.rest.controller;

import com.social.media.feed.account.service.application.mapper.AccountServiceMapper;
import com.social.media.feed.account.service.application.port.AccountService;
import com.social.media.feed.account.service.application.rest.model.RegisterAccountRequestBody;
import com.social.media.feed.account.service.domain.entity.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountServiceMapper accountServiceMapper;
    private final AccountService accountService;

    public AccountController(AccountServiceMapper accountServiceMapper, AccountService accountService) {
        this.accountServiceMapper = accountServiceMapper;
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> registerAccount(@RequestBody RegisterAccountRequestBody registerAccountRequestBody) {
        Account account = accountService.createAccount(accountServiceMapper.registerAccountRequestBodyToAccount(registerAccountRequestBody));
        return ResponseEntity.ok("Account created successfully with id " + account.getId().getValue() + " and username " + account.getUsername() + "!");
    }
}