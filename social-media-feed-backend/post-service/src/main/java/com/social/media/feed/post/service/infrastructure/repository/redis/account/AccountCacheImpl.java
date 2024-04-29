package com.social.media.feed.post.service.infrastructure.repository.redis.account;

import com.social.media.feed.application.rest.model.AccountResponse;
import com.social.media.feed.post.service.application.port.repository.AccountCache;
import com.social.media.feed.post.service.domain.entity.Account;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountCacheImpl implements AccountCache {

    private final RedisTemplate<String, Object> redisTemplate;

    public AccountCacheImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveAccount(Account account) {
        String key = account.getAccountId().toString();
        redisTemplate.opsForValue().set(key, account);
        redisTemplate.expire(key, 300, java.util.concurrent.TimeUnit.SECONDS);
    }

    @Override
    public Account getAccountByAccountUUID(String accountId) {
        return (Account) redisTemplate.opsForValue().get(accountId);
    }
}
