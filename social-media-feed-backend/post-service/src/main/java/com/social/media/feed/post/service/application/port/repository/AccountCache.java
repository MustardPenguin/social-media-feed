package com.social.media.feed.post.service.application.port.repository;

import com.social.media.feed.application.rest.model.AccountResponse;

public interface AccountCache {

    void saveAccount(AccountResponse accountResponse);

    String getAccount(String accountId);
}
