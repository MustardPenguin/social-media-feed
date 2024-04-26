package com.social.media.feed.post.service.application.mapper;

import com.social.media.feed.post.service.application.rest.model.CreatePostRequestBody;
import com.social.media.feed.post.service.domain.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostServiceMapper {

    public Post createPostRequestBodyToPost(CreatePostRequestBody createPostRequestBody) {
        return Post.builder()
                .title(createPostRequestBody.getTitle())
                .description(createPostRequestBody.getDescription())
                .build();
    }
}
