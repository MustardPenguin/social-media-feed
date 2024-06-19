package com.social.media.feed.feed.service;

import com.social.media.feed.feed.service.application.dto.FollowCreatedEventModel;
import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;
import com.social.media.feed.feed.service.application.dto.PostCreatedResponse;
import com.social.media.feed.feed.service.application.port.message.FollowCreatedMessageListener;
import com.social.media.feed.feed.service.application.port.message.PostCreatedMessageListener;
import com.social.media.feed.feed.service.application.port.repository.FollowRepository;
import com.social.media.feed.feed.service.application.rest.controller.FeedController;
import com.social.media.feed.feed.service.domain.entity.Follow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = {
        // Disables Kafka
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration",
        "spring.datasource.hikari.max-life = 600000",
        "eureka.client.enabled=false"})
public class FeedServiceApplicationTest extends BaseTest {

    @Autowired
    private FollowCreatedMessageListener followCreatedMessageListener;
    @Autowired
    private PostCreatedMessageListener postCreatedMessageListener;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private FeedController feedController;

    @Test
    public void feedServiceTest() {
        final UUID followerId = UUID.randomUUID();
        final UUID followeeId = UUID.randomUUID();

        // Test follow listener
        FollowCreatedEventModel followCreatedEventModel = FollowCreatedEventModel.builder()
                .followId(UUID.randomUUID())
                .followerId(followerId)
                .followeeId(followeeId)
                .build();

        followCreatedMessageListener.followCreated(followCreatedEventModel);

        // First one fetches from database then saves to Redis
        Follow follow = followRepository.getFollowByFollowerIdAndFolloweeId(followerId, followeeId);
        assertNotNull(follow);
        // Second one fetches from cache
        Follow followFromCache = followRepository.getFollowByFollowerIdAndFolloweeId(followerId, followeeId);
        assertNotNull(followFromCache);
        // Checks that the follow from database and follow from cache are the same
        assertEquals(follow.getFollowId(), followFromCache.getFollowId());
        assertEquals(follow.getFollowerId(), followFromCache.getFollowerId());
        assertEquals(follow.getFolloweeId(), followFromCache.getFolloweeId());

        // Prepare to subscribe to feed
        Flux<ServerSentEvent<PostCreatedResponse>> eventStream =
                feedController.streamEvents(followerId);

        PostCreatedEventModel postCreatedEventModel = PostCreatedEventModel.builder()
                .postId(UUID.randomUUID())
                .accountId(followeeId)
                .createdAt(LocalDateTime.now())
                .build();
        AtomicInteger postsReceived = new AtomicInteger();

        // Subscribe to feed
        eventStream.subscribe(
                event -> {
                    assertNotNull(event);
                    assertNotNull(event.data());
                    PostCreatedResponse postCreatedResponse = event.data();
                    assertEquals(postCreatedEventModel.getPostId(), postCreatedResponse.postId());
                    assertEquals(postCreatedEventModel.getAccountId(), postCreatedResponse.accountId());
                    postsReceived.addAndGet(1);
                }
        );

        postCreatedMessageListener.postCreated(postCreatedEventModel);
        assertEquals(1, postsReceived.get());

        // Test unfollow
        followRepository.deleteFollow(followCreatedEventModel);
        followCreatedMessageListener.followDeleted(followCreatedEventModel);
        // Since follower is unsubscribed, no posts should be received from eventstream, therefore, count should be 1
        assertEquals(1, postsReceived.get());
    }
}
