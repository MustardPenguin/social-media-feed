package com.social.media.feed.account.service.application.port.repository;

import com.social.media.feed.account.service.domain.entity.Account;

public interface AccountRepository {

    Account save(Account account);

    Account findAccountByUsername(String username);
}
