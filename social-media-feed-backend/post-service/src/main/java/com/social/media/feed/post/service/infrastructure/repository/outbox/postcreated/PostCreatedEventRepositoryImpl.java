package com.social.media.feed.post.service.infrastructure.repository.outbox.postcreated;

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

    public PostCreatedEventRepositoryImpl(PostCreatedEventJpaRepository postCreatedEventJpaRepository) {
        this.postCreatedEventJpaRepository = postCreatedEventJpaRepository;
    }

    @Override
    public PostCreatedEvent save(PostCreatedEvent postCreatedEvent) {
        try {
            PostCreatedEventEntity postCreatedEventEntity = postCreatedEventToEntity(postCreatedEvent);
            PostCreatedEventEntity response = postCreatedEventJpaRepository.save(postCreatedEventEntity);
            log.info("Post created event saved in outbox event table with id: {}", response.getEventId());
            return postCreatedEvent;
        } catch (Exception e) {
            log.error("Unable to save post created event in outbox event table. Error: {}", e.getMessage(), e);
            throw new PostDomainException("Unable to save post created event in outbox event table. Error " + e.getMessage());
        }
    }

    private PostCreatedEventEntity postCreatedEventToEntity(PostCreatedEvent postCreatedEvent) {
        Post post = postCreatedEvent.getEntity();
        return PostCreatedEventEntity.builder()
                .eventId(postCreatedEvent.getEventId())
                .postId(post.getId().getValue())
                .accountId(post.getAccountId())
                .build();
    }
}
