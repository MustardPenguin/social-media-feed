package com.social.media.feed.feed.service.application.impl;

import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;
import com.social.media.feed.feed.service.application.dto.PostCreatedResponse;
import com.social.media.feed.feed.service.application.port.message.PostCreatedMessageListener;
import com.social.media.feed.feed.service.application.port.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Slf4j
@Component
public class PostCreatedMessageListenerImpl implements PostCreatedMessageListener {

    private final PostRepository postRepository;
    private final Sinks.Many<PostCreatedResponse> eventSink;

    public PostCreatedMessageListenerImpl(PostRepository postRepository,
                                          Sinks.Many<PostCreatedResponse> eventSink) {
        this.postRepository = postRepository;
        this.eventSink = eventSink;
    }

    @Override
    @Transactional
    public void postCreated(PostCreatedEventModel postCreatedEventModel) {
        PostCreatedResponse postCreatedResponse = new PostCreatedResponse(postCreatedEventModel.getPostId(), postCreatedEventModel.getAccountId());
        postRepository.savePost(postCreatedEventModel);
        eventSink.emitNext(postCreatedResponse, (signalType, emitResult) -> {
            log.info("Signal type: {}", signalType);
            log.info("Emit result: {}", emitResult.name());
            // Return false to not retry emitting data
            return false;
        });
        log.info("Post created event emitted");
    }
}
