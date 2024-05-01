package com.social.media.feed.feed.service.infrastructure.message.post;

import com.social.media.feed.infrastructure.message.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PostCreatedKafkaListener implements KafkaConsumer<post_created.post.post_created_events.Envelope> {

    @Override
    @KafkaListener(topics = "${kafka-consumer-topics.post-created-event}", groupId = "feed-service-post-created-listener-group")
    public void receiveMessages(@Payload List<post_created.post.post_created_events.Envelope> messages) {
        log.info("Received post created event messages!");
        log.info("Messages: {}", messages);
    }
}
