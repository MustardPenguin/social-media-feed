package com.social.media.feed.account.service.domain.event;

import com.social.media.feed.account.service.domain.entity.Follow;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FollowCreatedEvent extends FollowEvent {

    private String op;

    public FollowCreatedEvent(Follow event, LocalDateTime localDateTime, String op) {
        super(event, localDateTime);
        this.op = op;
    }
}
