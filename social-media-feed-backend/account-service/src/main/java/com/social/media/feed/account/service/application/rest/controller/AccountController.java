package com.social.media.feed.account.service.application.rest.controller;

import com.social.media.feed.account.service.application.mapper.AccountServiceMapper;
import com.social.media.feed.account.service.application.port.service.AccountService;
import com.social.media.feed.account.service.application.rest.model.RegisterAccountRequestBody;
import com.social.media.feed.account.service.domain.entity.Account;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public ResponseEntity<String> registerAccount(@RequestBody @Valid RegisterAccountRequestBody registerAccountRequestBody) {
        Account account = accountService.createAccount(accountServiceMapper.registerAccountRequestBodyToAccount(registerAccountRequestBody));
        return ResponseEntity.ok("Account created successfully with id " + account.getId().getValue() + " and username " + account.getUsername() + "!");
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<String> getAccount(@PathVariable UUID accountId) {
        Account account = accountService.getAccountByAccountId(accountId);
        if(account == null) {
            return ResponseEntity.ok("Account not found with id " + accountId + "!");
        }
        return ResponseEntity.ok(account.getUsername());
    }
}
