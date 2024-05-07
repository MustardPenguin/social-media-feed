package com.social.media.feed.feed.service.application.port.message;

import com.social.media.feed.feed.service.application.dto.FollowCreatedEventModel;

public interface FollowCreatedMessageListener {

    void followCreated(FollowCreatedEventModel followCreatedEventModel);
}
