package com.social.media.feed.account.service.application.port.service;

import com.social.media.feed.account.service.domain.entity.Account;

public interface AuthenticationService {

    String authenticateAccount(Account account);
}
