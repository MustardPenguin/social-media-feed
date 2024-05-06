package com.social.media.feed.feed.service.application.impl;

import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;
import com.social.media.feed.feed.service.application.port.message.PostCreatedMessageListener;
import org.springframework.stereotype.Component;

@Component
public class PostCreatedMessageListenerImpl implements PostCreatedMessageListener {



    @Override
    public void postCreated(PostCreatedEventModel postCreatedEventModel) {


    }
}
