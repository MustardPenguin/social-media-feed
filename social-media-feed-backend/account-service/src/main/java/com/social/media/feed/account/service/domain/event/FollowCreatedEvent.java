package com.social.media.feed.account.service.domain.event;

import com.social.media.feed.account.service.domain.entity.Follow;

import java.time.LocalDateTime;

public class FollowCreatedEvent extends FollowEvent {
    public FollowCreatedEvent(Follow event, LocalDateTime localDateTime) {
        super(event, localDateTime);
    }
}
