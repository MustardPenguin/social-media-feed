package com.social.media.feed.feed.service.application.impl;

import com.social.media.feed.feed.service.application.dto.PostCreatedResponse;
import com.social.media.feed.feed.service.application.port.service.FeedService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.bootstrap.SSLServerSetupHandler;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class FeedServiceImpl implements FeedService {

    private final Sinks.Many<PostCreatedResponse> eventSink;

    public FeedServiceImpl(Sinks.Many<PostCreatedResponse> eventSink) {
        this.eventSink = eventSink;
    }

    @Override
    public Flux<ServerSentEvent<PostCreatedResponse>> subscribeToFeed(UUID accountId) {
        log.info("Incoming subscription request for account {}", accountId);
        final AtomicLong counter = new AtomicLong(0);
        return eventSink.asFlux().map(postCreatedResponse -> {
            log.info("Test!");
            return ServerSentEvent.builder(postCreatedResponse)
                   .id(counter.incrementAndGet() + "")
                   .build();
        });
    }
}