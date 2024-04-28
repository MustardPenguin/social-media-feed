package com.social.media.feed.post.service.infrastructure.repository.redis.account;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Builder
@RedisHash("account")
public class AccountEntity implements Serializable {

    private UUID accountId;
    private String username;
}
