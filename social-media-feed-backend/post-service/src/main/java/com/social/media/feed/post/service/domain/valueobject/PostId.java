package com.social.media.feed.post.service.domain.valueobject;

import com.social.media.feed.domain.valueobject.BaseId;

import java.util.UUID;

public class PostId extends BaseId {
    public PostId(UUID id) {
        super(id);
    }
}
