package com.social.media.feed.post.service.infrastructure.repository.account;

import com.social.media.feed.application.rest.model.AccountResponse;
import com.social.media.feed.application.util.ObjectMapperUtil;
import com.social.media.feed.post.service.application.port.repository.AccountRepository;
import com.social.media.feed.post.service.application.util.PostFeignClient;
import com.social.media.feed.post.service.domain.entity.Account;
import com.social.media.feed.post.service.domain.exception.PostDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class AccountRepositoryImpl implements AccountRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapperUtil objectMapperUtil;
    private final PostFeignClient postFeignClient;

    public AccountRepositoryImpl(RedisTemplate<String, Object> redisTemplate,
                                 ObjectMapperUtil objectMapperUtil,
                                 PostFeignClient postFeignClient) {
        this.redisTemplate = redisTemplate;
        this.objectMapperUtil = objectMapperUtil;
        this.postFeignClient = postFeignClient;
    }

    @Override
    public Account getAccountByAccountUUID(UUID accountId) {
        Account account = getAccountFromCache(accountId);
        if(account == null) {
            account = getAccountResponseByAccountIdFromAccountService(accountId);
            saveAccountToCache(account);
            log.info("Account info not found in cache, fetched from account service and saved to cache!");
        }
        return account;
    }

    private void saveAccountToCache(Account account) {
        String key = account.getAccountId().toString();
        redisTemplate.opsForValue().set(key, account);
        redisTemplate.expire(key, 300, java.util.concurrent.TimeUnit.SECONDS);
    }

    private Account getAccountFromCache(UUID accountId) {
        return (Account) redisTemplate.opsForValue().get(accountId.toString());
    }

    private Account getAccountResponseByAccountIdFromAccountService(UUID accountId) {
        String response = postFeignClient.getAccountByAccountId(accountId);
        log.info("Found account of username " + response + " for account id " + accountId + "!");
        if(response == null || response.isEmpty()) {
            throw new PostDomainException("Account not found for account id " + accountId + " in account service!");
        }
        AccountResponse accountResponse = objectMapperUtil.convertStringToObject(response, AccountResponse.class);
        return Account.builder()
                .accountId(accountResponse.getAccountId())
                .username(accountResponse.getUsername())
                .build();
    }
}
