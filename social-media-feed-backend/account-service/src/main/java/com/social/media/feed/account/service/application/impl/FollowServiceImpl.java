package com.social.media.feed.account.service.application.impl;

import com.social.media.feed.account.service.application.port.repository.AccountRepository;
import com.social.media.feed.account.service.application.port.repository.FollowCreatedEventRepository;
import com.social.media.feed.account.service.application.port.repository.FollowRepository;
import com.social.media.feed.account.service.application.port.service.FollowService;
import com.social.media.feed.account.service.domain.entity.Account;
import com.social.media.feed.account.service.domain.entity.Follow;
import com.social.media.feed.account.service.domain.event.FollowCreatedEvent;
import com.social.media.feed.account.service.domain.exception.AccountDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class FollowServiceImpl implements FollowService {

    private final FollowCreatedEventRepository followCreatedEventRepository;
    private final AccountRepository accountRepository;
    private final FollowRepository followRepository;

    public FollowServiceImpl(FollowCreatedEventRepository followCreatedEventRepository,
                             AccountRepository accountRepository,
                             FollowRepository followRepository) {
        this.followCreatedEventRepository = followCreatedEventRepository;
        this.accountRepository = accountRepository;
        this.followRepository = followRepository;
    }

    @Override
    @Transactional
    public Follow followAccount(UUID followerId, UUID followeeId) {
        log.info("Requesting follow for followerId {} and followeeId {}", followerId, followeeId);
        validateAccountExistence(followerId);
        validateAccountExistence(followeeId);
        Follow follow = Follow.builder()
                .followId(UUID.randomUUID())
                .followeeId(followeeId)
                .followerId(followerId)
                .build();

        Follow existingFollow = followRepository.findFollowByFollowerIdAndFolloweeId(follow);
        if(existingFollow != null) {
            throw new AccountDomainException("Already following the account with accountId " + followeeId);
        }
        Follow response = followRepository.saveFollow(follow);
        FollowCreatedEvent followCreatedEvent = new FollowCreatedEvent(response, LocalDateTime.now());
        followCreatedEventRepository.saveFollowCreatedEvent(followCreatedEvent);

        return response;
    }

    private Account validateAccountExistence(UUID accountId) {
        Account account = accountRepository.findAccountByAccountId(accountId);
        if(account == null) {
            throw new AccountDomainException("Account not found with accountId " + accountId);
        }
        return account;
    }
}
