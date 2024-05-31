package com.social.media.feed.account.service.application.impl;

import com.social.media.feed.account.service.application.dto.FollowWithUsername;
import com.social.media.feed.account.service.application.port.repository.AccountRepository;
import com.social.media.feed.account.service.application.port.repository.FollowCreatedEventRepository;
import com.social.media.feed.account.service.application.port.repository.FollowRepository;
import com.social.media.feed.account.service.application.port.service.FollowService;
import com.social.media.feed.account.service.application.util.AccountServiceUtil;
import com.social.media.feed.account.service.domain.entity.Account;
import com.social.media.feed.account.service.domain.entity.Follow;
import com.social.media.feed.account.service.domain.event.FollowCreatedEvent;
import com.social.media.feed.account.service.domain.exception.AccountDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FollowServiceImpl implements FollowService {

    private final FollowCreatedEventRepository followCreatedEventRepository;
    private final AccountServiceUtil accountServiceUtil;
    private final AccountRepository accountRepository;
    private final FollowRepository followRepository;

    public FollowServiceImpl(FollowCreatedEventRepository followCreatedEventRepository,
                             AccountServiceUtil accountServiceUtil,
                             AccountRepository accountRepository,
                             FollowRepository followRepository) {
        this.followCreatedEventRepository = followCreatedEventRepository;
        this.accountServiceUtil = accountServiceUtil;
        this.accountRepository = accountRepository;
        this.followRepository = followRepository;
    }

    @Transactional
    public Follow followAccount(UUID followerId, UUID followeeId) {
        log.info("Requesting follow for followerId {} and followeeId {}", followerId, followeeId);
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

    @Transactional
    public Follow unfollowAccount(UUID followerId, UUID followeeId) {
        log.info("Requesting unfollow for followerId {} and followeeId {}", followerId, followeeId);
        Follow follow = followRepository.findFollowByFollowerIdAndFolloweeId(Follow.builder()
                .followeeId(followeeId)
                .followerId(followerId)
                .build());
        if(follow == null) {
            throw new AccountDomainException("Not following the account with accountId " + followeeId + "!");
        }
        followRepository.deleteFollow(follow);
        return follow;
    }

    @Override
    @Transactional
    public FollowWithUsername followAccountWithUsername(UUID followerId, String followeeUsername) {
        Account followee = accountServiceUtil.validateAccountExistence(followeeUsername);
        followAccount(followerId, followee.getId().getValue());
        return new FollowWithUsername(followee.getId().getValue(), followee.getUsername());
    }

    @Override
    @Transactional
    public FollowWithUsername followAccountWithUUID(UUID followerId, UUID followeeId) {
        Account followee = accountServiceUtil.validateAccountExistence(followeeId);
        followAccount(followerId, followeeId);
        return new FollowWithUsername(followeeId, followee.getUsername());
    }


    @Override
    @Transactional
    public FollowWithUsername unfollowAccountWithUUID(UUID followerId, UUID followeeId) {
        Account account = accountServiceUtil.validateAccountExistence(followeeId);
        Follow follow = unfollowAccount(followerId, followeeId);
        return new FollowWithUsername(follow.getFolloweeId(), account.getUsername());
    }

    @Override
    @Transactional
    public FollowWithUsername unfollowAccountWithUsername(UUID followerId, String followeeUsername) {
        Account account = accountServiceUtil.validateAccountExistence(followeeUsername);
        Follow follow = unfollowAccount(followerId, account.getId().getValue());
        return new FollowWithUsername(follow.getFolloweeId(), account.getUsername());
    }
    @Override
    public List<FollowWithUsername> getFollowersByAccountId(UUID accountId) {
        accountServiceUtil.validateAccountExistence(accountId);
        return followRepository.getFollowersByAccountId(accountId);
    }

    @Override
    public List<FollowWithUsername> getFolloweesByAccountId(UUID accountId) {
        accountServiceUtil.validateAccountExistence(accountId);
        return followRepository.getFolloweesByAccountId(accountId);
    }
}
