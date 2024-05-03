package com.social.media.feed.post.service.infrastructure.repository.post;

import com.social.media.feed.post.service.domain.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostRepositoryMapper {

    public PostEntity postToPostEntity(Post post) {
        return PostEntity.builder()
                .postId(post.getId().getValue())
                .accountId(post.getAccountId())
                .title(post.getTitle())
                .description(post.getDescription())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public Post postEntityToPost(PostEntity postEntity) {
        return Post.builder()
                .postId(postEntity.getPostId())
                .accountId(postEntity.getAccountId())
                .title(postEntity.getTitle())
                .description(postEntity.getDescription())
                .build();
    }
}
