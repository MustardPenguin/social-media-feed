package com.social.media.feed.feed.service.infrastructure.message;

import com.social.media.feed.application.util.ObjectMapperUtil;
import com.social.media.feed.feed.service.application.dto.FollowCreatedEventModel;
import com.social.media.feed.feed.service.application.port.message.FollowCreatedMessageListener;
import com.social.media.feed.infrastructure.message.KafkaConsumer;
import com.social.media.feed.infrastructure.message.dto.payload.FollowCreatedEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FollowCreatedKafkaListener implements KafkaConsumer<follow_created.account.follow_created_events.Envelope> {

    private final FollowCreatedMessageListener followCreatedMessageListener;
    private final ObjectMapperUtil objectMapperUtil;

    public FollowCreatedKafkaListener(FollowCreatedMessageListener followCreatedMessageListener, ObjectMapperUtil objectMapperUtil) {
        this.followCreatedMessageListener = followCreatedMessageListener;
        this.objectMapperUtil = objectMapperUtil;
    }

    @Override
    @KafkaListener(topics = "${kafka-consumer-topics.follow-created-event}", groupId = "feed-service-follow-created-listener-group")
    public void receiveMessages(List<follow_created.account.follow_created_events.Envelope> messages) {
        log.info("Received follow created event messages!");
        log.info("Messages: {}", messages);
        messages.forEach(message -> {
            follow_created.account.follow_created_events.Value value = message.getAfter();
            FollowCreatedEventPayload followCreatedEventPayload =
                    objectMapperUtil.convertStringToObject(value.getPayload(), FollowCreatedEventPayload.class);
            FollowCreatedEventModel followCreatedEventModel = followCreatedEventPayloadToModel(followCreatedEventPayload);
            if(followCreatedEventPayload.getOp().equals("C")) {
                followCreatedMessageListener.followCreated(followCreatedEventModel);
            } else if(followCreatedEventPayload.getOp().equals("D")) {
                followCreatedMessageListener.followDeleted(followCreatedEventModel);
            }
        });
    }

    private FollowCreatedEventModel followCreatedEventPayloadToModel(FollowCreatedEventPayload followCreatedEventPayload) {
        return FollowCreatedEventModel.builder()
                .followId(followCreatedEventPayload.getFollowId())
                .followerId(followCreatedEventPayload.getFollowerId())
                .followeeId(followCreatedEventPayload.getFolloweeId())
                .build();
    }
}
