package com.social.media.feed.account.service.infrastructure.repository.account;

import com.social.media.feed.account.service.domain.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountRepositoryMapper {

    public AccountEntity accountToAccountEntity(Account account) {
        return AccountEntity.builder()
                .accountId(account.getId().getValue())
                .username(account.getUsername())
                .password(account.getPassword())
                .build();
    }

    public Account accountEntityToAccount(AccountEntity accountEntity) {
        return Account.builder()
                .accountId(accountEntity.getAccountId())
                .username(accountEntity.getUsername())
                .password(accountEntity.getPassword())
                .build();
    }
}
