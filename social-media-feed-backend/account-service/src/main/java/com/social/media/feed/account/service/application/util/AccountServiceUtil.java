package com.social.media.feed.account.service.application.util;

import com.social.media.feed.account.service.application.port.repository.AccountRepository;
import com.social.media.feed.account.service.domain.entity.Account;
import com.social.media.feed.account.service.domain.exception.AccountDomainException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountServiceUtil {

    private final AccountRepository accountRepository;

    public AccountServiceUtil(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account validateAccountExistence(UUID accountId) {
        Account account = accountRepository.findAccountByAccountId(accountId);
        if(account == null) {
            throw new AccountDomainException("Account not found with accountId " + accountId);
        }
        return account;
    }

    public Account validateAccountExistence(String username) {
        Account account = accountRepository.findAccountByUsername(username);
        if(account == null) {
            throw new AccountDomainException("Account not found with username " + username);
        }
        return account;
    }
}
