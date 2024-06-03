package com.social.media.feed.post.service.application.port.repository;

import com.social.media.feed.post.service.domain.entity.Post;

import java.util.UUID;

public interface PostRepository {
    Post save(Post post);

    Post getPostById(UUID postId);
}
