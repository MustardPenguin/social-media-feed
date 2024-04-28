package com.social.media.feed.post.service.infrastructure.repository.redis.account;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("account")
public class AccountEntity implements Serializable {

    private UUID accountId;
    private String username;
}
