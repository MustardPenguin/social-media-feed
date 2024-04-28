package com.social.media.feed.post.service.infrastructure.repository.redis.account;

import com.social.media.feed.application.rest.model.AccountResponse;
import com.social.media.feed.post.service.application.port.repository.AccountCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountCacheImpl implements AccountCache {

    private final RedisTemplate<String, Object> redisTemplate;

    public AccountCacheImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void saveAccount(AccountResponse accountResponse) {
        AccountEntity accountEntity = AccountEntity.builder()
                .accountId(accountResponse.getAccountId())
                .username(accountResponse.getUsername())
                .build();
        redisTemplate.opsForValue().set(accountResponse.getAccountId().toString(), accountEntity);
    }

    @Override
    public String getAccount(String accountId) {
        return null;
    }
}
