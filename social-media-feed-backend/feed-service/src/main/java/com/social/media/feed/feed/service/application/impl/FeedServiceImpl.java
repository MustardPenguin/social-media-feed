package com.social.media.feed.feed.service.application.impl;

import com.social.media.feed.feed.service.application.dto.PostCreatedResponse;
import com.social.media.feed.feed.service.application.port.repository.FollowRepository;
import com.social.media.feed.feed.service.application.port.service.FeedService;
import com.social.media.feed.feed.service.domain.entity.Follow;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.bootstrap.SSLServerSetupHandler;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class FeedServiceImpl implements FeedService {

    private final FollowRepository followRepository;
    private final Sinks.Many<PostCreatedResponse> eventSink;

    public FeedServiceImpl(FollowRepository followRepository,
                           Sinks.Many<PostCreatedResponse> eventSink) {
        this.followRepository = followRepository;
        this.eventSink = eventSink;
    }

    @Override
    public Flux<ServerSentEvent<PostCreatedResponse>> subscribeToFeed(UUID accountId) {
        log.info("Incoming subscription request for account {}", accountId);
        final AtomicLong counter = new AtomicLong(0);
        return eventSink.asFlux()
                .filter(postCreatedResponse -> {
                    // Check if the post creator is followed by the account
                    Follow follow = followRepository.getFollowByFollowerIdAndFolloweeId(accountId, postCreatedResponse.accountId());
                    return follow != null;
                })
                .map(postCreatedResponse -> {
            log.info("Sending post created event to account id {}", accountId);
            return ServerSentEvent.builder(postCreatedResponse)
                   .id(counter.incrementAndGet() + "")
                   .build();
        });
    }
}