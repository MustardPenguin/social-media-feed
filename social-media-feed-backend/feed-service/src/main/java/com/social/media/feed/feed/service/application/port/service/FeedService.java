package com.social.media.feed.feed.service.application.port.service;

import com.social.media.feed.feed.service.application.dto.PostCreatedResponse;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface FeedService {

    Flux<ServerSentEvent<PostCreatedResponse>> subscribeToFeed(UUID accountId);
}
