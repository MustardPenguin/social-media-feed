package com.social.media.feed.account.service.application.port.service;

import com.social.media.feed.account.service.domain.entity.Account;

import java.util.UUID;

public interface AccountService {

    Account createAccount(Account account);

    Account getAccountByAccountId(UUID accountId);
}
