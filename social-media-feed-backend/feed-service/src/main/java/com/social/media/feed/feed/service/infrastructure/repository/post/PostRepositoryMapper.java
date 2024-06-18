package com.social.media.feed.feed.service.infrastructure.repository.post;

import com.social.media.feed.feed.service.application.dto.PostCreatedEventModel;
import org.springframework.stereotype.Component;

@Component
public class PostRepositoryMapper {

    public PostEntity postCreatedEventModelToPostEntity(PostCreatedEventModel postCreatedEventModel) {
        return PostEntity.builder()
                .postId(postCreatedEventModel.getPostId())
                .accountId(postCreatedEventModel.getAccountId())
                .createdAt(postCreatedEventModel.getCreatedAt())
                .build();
    }

    public PostCreatedEventModel postEntityToPostCreatedEventModel(PostEntity postEntity) {
        return PostCreatedEventModel.builder()
                .postId(postEntity.getPostId())
                .accountId(postEntity.getAccountId())
                .createdAt(postEntity.getCreatedAt())
                .build();
    }
}
