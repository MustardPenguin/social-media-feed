package com.social.media.feed.account.service.infrastructure.repository.outbox.followcreated;

import com.social.media.feed.account.service.application.port.repository.FollowCreatedEventRepository;
import com.social.media.feed.account.service.domain.entity.Follow;
import com.social.media.feed.account.service.domain.event.FollowCreatedEvent;
import com.social.media.feed.application.util.ObjectMapperUtil;
import com.social.media.feed.infrastructure.message.dto.payload.FollowCreatedEventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FollowCreatedEventRepositoryImpl implements FollowCreatedEventRepository {

    private final FollowCreatedEventJpaRepository followCreatedEventJpaRepository;
    private final ObjectMapperUtil objectMapperUtil;

    public FollowCreatedEventRepositoryImpl(FollowCreatedEventJpaRepository followCreatedEventJpaRepository, ObjectMapperUtil objectMapperUtil) {
        this.followCreatedEventJpaRepository = followCreatedEventJpaRepository;
        this.objectMapperUtil = objectMapperUtil;
    }

    @Override
    public FollowCreatedEvent saveFollowCreatedEvent(FollowCreatedEvent followCreatedEvent) {
        try {
            FollowCreatedEventEntity followCreatedEventEntity = createdEventEntityFromEvent(followCreatedEvent);
            followCreatedEventJpaRepository.save(followCreatedEventEntity);
            return followCreatedEvent;
        } catch (Exception e) {
            log.error("Unable to save follow created event in outbox event table. Error: {}", e.getMessage(), e);
            throw new RuntimeException("Unable to save follow created event in outbox event table. Error " + e.getMessage());
        }
    }

    private FollowCreatedEventEntity createdEventEntityFromEvent(FollowCreatedEvent followCreatedEvent) {
        Follow follow = followCreatedEvent.getEntity();
        FollowCreatedEventPayload followCreatedEventPayload = FollowCreatedEventPayload.builder()
                .followId(follow.getId().getValue())
                .followerId(follow.getFollowerId())
                .followeeId(follow.getFolloweeId())
                .build();
        String payload = objectMapperUtil.convertObjectToString(followCreatedEventPayload);
        return FollowCreatedEventEntity.builder()
                .createdAt(followCreatedEvent.getCreatedAt())
                .eventId(followCreatedEvent.getEventId())
                .payload(payload)
                .build();
    }
}
