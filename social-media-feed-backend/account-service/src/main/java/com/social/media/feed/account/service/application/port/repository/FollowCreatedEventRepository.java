package com.social.media.feed.account.service.application.port.repository;

import com.social.media.feed.account.service.domain.event.FollowCreatedEvent;

public interface FollowCreatedEventRepository {

    FollowCreatedEvent saveFollowCreatedEvent(FollowCreatedEvent followCreatedEvent);
}
