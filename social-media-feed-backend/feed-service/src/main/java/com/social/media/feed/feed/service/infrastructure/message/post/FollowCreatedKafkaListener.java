package com.social.media.feed.feed.service.infrastructure.message.post;

import com.social.media.feed.infrastructure.message.KafkaConsumer;
import follow_created.account.follow_created_event.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FollowCreatedKafkaListener implements KafkaConsumer<follow_created.account.follow_created_event.Envelope> {

    @Override
    @KafkaListener(topics = "${kafka-consumer-topics.follow-created-event}", groupId = "feed-service-follow-created-listener-group")
    public void receiveMessages(List<Envelope> messages) {
        log.info("Received follow created event messages!");
        log.info("Messages: {}", messages);
    }
}
