package com.social.media.feed.feed.service.application.impl;

import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;
import com.social.media.feed.feed.service.application.dto.PostCreatedResponse;
import com.social.media.feed.feed.service.application.port.message.PostCreatedMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Slf4j
@Component
public class PostCreatedMessageListenerImpl implements PostCreatedMessageListener {

    private final Sinks.Many<PostCreatedResponse> eventSink;

    public PostCreatedMessageListenerImpl(Sinks.Many<PostCreatedResponse> eventSink) {
        this.eventSink = eventSink;
    }

    @Override
    @Transactional
    public void postCreated(PostCreatedEventModel postCreatedEventModel) {
        eventSink.emitNext(new PostCreatedResponse(UUID.randomUUID(), UUID.randomUUID()), (signalType, emitResult) -> emitResult.isFailure());
        log.info("Post created event emitted");
    }
}
