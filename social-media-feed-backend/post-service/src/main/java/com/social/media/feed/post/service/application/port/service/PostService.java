package com.social.media.feed.post.service.application.port.service;

import com.social.media.feed.post.service.domain.entity.Post;

import java.util.UUID;

public interface PostService {

    Post createPost(Post post);

    Post getPostById(UUID postId);
}
