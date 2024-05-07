package com.social.media.feed.feed.service.infrastructure.message;

import com.social.media.feed.application.util.ObjectMapperUtil;
import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;
import com.social.media.feed.feed.service.application.port.message.PostCreatedMessageListener;
import com.social.media.feed.infrastructure.message.KafkaConsumer;
import com.social.media.feed.infrastructure.message.dto.payload.PostCreatedEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@Component
public class PostCreatedKafkaListener implements KafkaConsumer<post_created.post.post_created_events.Envelope> {

    private final PostCreatedMessageListener postCreatedMessageListener;
    private final ObjectMapperUtil objectMapperUtil;

    public PostCreatedKafkaListener(PostCreatedMessageListener postCreatedMessageListener, ObjectMapperUtil objectMapperUtil) {
        this.postCreatedMessageListener = postCreatedMessageListener;
        this.objectMapperUtil = objectMapperUtil;
    }

    @Override
    @KafkaListener(topics = "${kafka-consumer-topics.post-created-event}", groupId = "feed-service-post-created-listener-group")
    public void receiveMessages(@Payload List<post_created.post.post_created_events.Envelope> messages) {
        log.info("Received post created event messages!");
        log.info("Messages: {}", messages);
        messages.forEach(message -> {
            post_created.post.post_created_events.Value value = message.getAfter();
            PostCreatedEventPayload postCreatedEventPayload = objectMapperUtil.convertStringToObject(value.getPayload(), PostCreatedEventPayload.class);

            long timestamp = value.getCreatedAt();
            LocalDateTime localDateTime = convertTimestampToLocalDateTime(timestamp);

            log.info("Timestamp: {}, Date time: {}", timestamp, localDateTime);
            postCreatedMessageListener.postCreated(postCreatedEventToModel(postCreatedEventPayload, localDateTime));
        });
    }

    private PostCreatedEventModel postCreatedEventToModel(PostCreatedEventPayload postCreatedEventPayload, LocalDateTime createdAt) {
        return PostCreatedEventModel.builder()
                .accountId(postCreatedEventPayload.getAccountId())
                .postId(postCreatedEventPayload.getPostId())
                .createdAt(createdAt)
                .build();
    }

    private LocalDateTime convertTimestampToLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp / 1000), ZoneOffset.UTC);
    }
}
