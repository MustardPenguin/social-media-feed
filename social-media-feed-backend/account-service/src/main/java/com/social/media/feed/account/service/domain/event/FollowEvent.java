package com.social.media.feed.account.service.domain.event;

import com.social.media.feed.account.service.domain.entity.Follow;
import com.social.media.feed.domain.event.DomainEvent;

import java.time.LocalDateTime;

public class FollowEvent extends DomainEvent<Follow> {
    public FollowEvent(Follow event, LocalDateTime localDateTime) {
        super(event, localDateTime);
    }
}
