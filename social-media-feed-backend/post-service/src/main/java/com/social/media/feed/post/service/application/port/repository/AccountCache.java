package com.social.media.feed.post.service.application.port.repository;

import com.social.media.feed.application.rest.model.AccountResponse;
import com.social.media.feed.post.service.domain.entity.Account;

public interface AccountCache {

    void saveAccount(Account account);

    Account getAccountByAccountUUID(String accountId);
}
