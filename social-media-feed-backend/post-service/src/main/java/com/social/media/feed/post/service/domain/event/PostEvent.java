package com.social.media.feed.post.service.domain.event;

import com.social.media.feed.domain.event.DomainEvent;
import com.social.media.feed.post.service.domain.entity.Post;

import java.time.LocalDateTime;
import java.util.UUID;

public class PostEvent extends DomainEvent<Post> {
    public PostEvent(Post entity, LocalDateTime createdAt) {
        super(entity, createdAt);
    }
}
