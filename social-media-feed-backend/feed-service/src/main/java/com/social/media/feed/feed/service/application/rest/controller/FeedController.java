package com.social.media.feed.feed.service.application.rest.controller;

import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;
import com.social.media.feed.feed.service.application.dto.PostCreatedResponse;
import com.social.media.feed.feed.service.application.port.service.FeedService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final FeedService feedService;

    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping(value = "/{accountId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<PostCreatedResponse>> streamEvents(@PathVariable UUID accountId) {
        return feedService.subscribeToFeed(accountId);
    }

    @GetMapping(value = "/{accountId}/posts")
    public List<PostCreatedEventModel> generateInitialFeed(@PathVariable UUID accountId) {
        return feedService.generateInitialFeed(accountId);
    }
}
