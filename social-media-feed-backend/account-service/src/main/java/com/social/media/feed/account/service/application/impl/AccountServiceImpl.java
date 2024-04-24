package com.social.media.feed.account.service.application.impl;

import com.social.media.feed.account.service.application.port.AccountRepository;
import com.social.media.feed.account.service.application.port.AccountService;
import com.social.media.feed.account.service.domain.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(Account account) {
        log.info("Creating account for username {} at service layer", account.getUsername());
        return accountRepository.save(account);
    }
}
