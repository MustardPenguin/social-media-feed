package com.social.media.feed.post.service.domain.event;

import com.social.media.feed.domain.event.DomainEvent;
import com.social.media.feed.post.service.domain.entity.Post;

import java.time.LocalDateTime;

public class PostCreatedEvent extends DomainEvent<Post> {

    public PostCreatedEvent(Post entity, LocalDateTime createdAt) {
        super(entity, createdAt);
    }
}
