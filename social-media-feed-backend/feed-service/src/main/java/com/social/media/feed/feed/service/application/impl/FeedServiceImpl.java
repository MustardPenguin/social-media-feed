package com.social.media.feed.feed.service.application.impl;

import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;
import com.social.media.feed.feed.service.application.dto.PostCreatedResponse;
import com.social.media.feed.feed.service.application.port.repository.FollowRepository;
import com.social.media.feed.feed.service.application.port.repository.PostRepository;
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
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class FeedServiceImpl implements FeedService {

    private final FollowRepository followRepository;
    private final PostRepository postRepository;
    private final Sinks.Many<PostCreatedResponse> eventSink;

    public FeedServiceImpl(FollowRepository followRepository,
                           PostRepository postRepository,
                           Sinks.Many<PostCreatedResponse> eventSink) {
        this.followRepository = followRepository;
        this.postRepository = postRepository;
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

    @Override
    public List<PostCreatedEventModel> generateInitialFeed(UUID accountId) {
        List<Follow> followees = followRepository.getFolloweesByFollowerId(accountId);
        log.info("Fetched followees of size: {}", followees.size());
        for(Follow follow : followees) {
            log.info("Followee: {}", follow.getFolloweeId());
        }
        List<PostCreatedEventModel> postCreatedEventModels = postRepository
                .getRelevantPostsByAccountIds(followees.stream().map(Follow::getFolloweeId).toList());
        return postCreatedEventModels;
    }
}