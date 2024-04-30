package com.social.media.feed.feed.service.infrastructure.message.post;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostCreatedKafkaListener {

    @KafkaListener(topics = "${kafka-consumer-topics.post-created-event}", groupId = "feed-service-post-created-listener-group")
    public void onPostCreated() {
        log.info("Received post created event!");
    }
}
