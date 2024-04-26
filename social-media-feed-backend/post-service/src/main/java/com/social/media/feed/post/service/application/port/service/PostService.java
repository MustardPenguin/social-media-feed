package com.social.media.feed.post.service.application.port.service;

import com.social.media.feed.post.service.domain.entity.Post;

public interface PostService {

    Post createPost(Post post);
}
