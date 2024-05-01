package com.social.media.feed.post.service.application.port.repository;

import com.social.media.feed.post.service.domain.event.PostCreatedEvent;

public interface PostCreatedEventRepository {

    PostCreatedEvent save(PostCreatedEvent postCreatedEvent);
}
