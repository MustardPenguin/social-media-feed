package com.social.media.feed.post.service.application.port.repository;

import com.social.media.feed.post.service.domain.entity.Account;

import java.util.UUID;

public interface AccountRepository {

    Account getAccountByAccountUUID(UUID accountId);
}
