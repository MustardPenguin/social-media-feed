package com.social.media.feed.post.service.infrastructure.repository.outbox.postcreated;

import com.social.media.feed.application.util.ObjectMapperUtil;
import com.social.media.feed.infrastructure.message.dto.payload.PostCreatedEventPayload;
import com.social.media.feed.post.service.application.port.repository.PostCreatedEventRepository;
import com.social.media.feed.post.service.domain.entity.Post;
import com.social.media.feed.post.service.domain.event.PostCreatedEvent;
import com.social.media.feed.post.service.domain.exception.PostDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostCreatedEventRepositoryImpl implements PostCreatedEventRepository {

    private final PostCreatedEventJpaRepository postCreatedEventJpaRepository;
    private final ObjectMapperUtil objectMapperUtil;

    public PostCreatedEventRepositoryImpl(PostCreatedEventJpaRepository postCreatedEventJpaRepository, ObjectMapperUtil objectMapperUtil) {
        this.postCreatedEventJpaRepository = postCreatedEventJpaRepository;
        this.objectMapperUtil = objectMapperUtil;
    }

    @Override
    public PostCreatedEvent save(PostCreatedEvent postCreatedEvent) {
        try {
            PostCreatedEventEntity postCreatedEventEntity = createdEventEntityFromEvent(postCreatedEvent);
            PostCreatedEventEntity response = postCreatedEventJpaRepository.save(postCreatedEventEntity);
            log.info("Post created event saved in outbox event table with id: {}", response.getEventId());
            return postCreatedEvent;
        } catch (Exception e) {
            log.error("Unable to save post created event in outbox event table. Error: {}", e.getMessage(), e);
            throw new PostDomainException("Unable to save post created event in outbox event table. Error " + e.getMessage());
        }
    }

    private PostCreatedEventEntity createdEventEntityFromEvent(PostCreatedEvent postCreatedEvent) {
        Post post = postCreatedEvent.getEntity();
        PostCreatedEventPayload postCreatedEventPayload = PostCreatedEventPayload.builder()
                .postId(post.getId().getValue())
                .accountId(post.getAccountId())
                .build();
        String payload = objectMapperUtil.convertObjectToString(postCreatedEventPayload);
        return PostCreatedEventEntity.builder()
                .createdAt(postCreatedEvent.getCreatedAt())
                .eventId(postCreatedEvent.getEventId())
                .payload(payload)
                .build();
    }
}
