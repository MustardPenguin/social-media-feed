package com.social.media.feed.feed.service.application.impl;

import com.social.media.feed.feed.service.application.dto.FollowCreatedEventModel;
import com.social.media.feed.feed.service.application.port.message.FollowCreatedMessageListener;
import com.social.media.feed.feed.service.application.port.repository.FollowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class FollowCreatedMessageListenerImpl implements FollowCreatedMessageListener {

    private final FollowRepository followRepository;

    public FollowCreatedMessageListenerImpl(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    @Override
    @Transactional
    public void followCreated(FollowCreatedEventModel followCreatedEventModel) {
        FollowCreatedEventModel response = followRepository.saveFollow(followCreatedEventModel);
        log.info("Follow saved successfully of id {}", response.getFollowId());
    }
}
