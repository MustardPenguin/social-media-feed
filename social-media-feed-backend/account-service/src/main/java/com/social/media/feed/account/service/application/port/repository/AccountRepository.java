package com.social.media.feed.account.service.application.port.repository;

import com.social.media.feed.account.service.domain.entity.Account;

import java.util.UUID;

public interface AccountRepository {

    Account save(Account account);

    Account findAccountByUsername(String username);

    Account findAccountByAccountId(UUID accountId);
}
