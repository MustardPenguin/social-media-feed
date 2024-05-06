package com.social.media.feed.feed.service.application.port.message;

import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;

public interface PostCreatedMessageListener {

    void postCreated(PostCreatedEventModel postCreatedEventModel);
}
