package com.social.media.feed.post.service.application.mapper;

import com.social.media.feed.post.service.application.rest.model.CreatePostRequestBody;
import com.social.media.feed.post.service.application.rest.model.PostResponse;
import com.social.media.feed.post.service.application.util.PostServiceUtil;
import com.social.media.feed.post.service.domain.entity.Account;
import com.social.media.feed.post.service.domain.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostServiceMapper {

    private final PostServiceUtil postServiceUtil;

    public PostServiceMapper(PostServiceUtil postServiceUtil) {
        this.postServiceUtil = postServiceUtil;
    }

    public Post createPostRequestBodyToPost(CreatePostRequestBody createPostRequestBody) {
        return Post.builder()
                .title(createPostRequestBody.getTitle())
                .description(createPostRequestBody.getDescription())
                .build();
    }

    public PostResponse postToPostResponse(Post post) {
        Account account = postServiceUtil.getAccountByAccountId(post.getAccountId());
        return PostResponse.builder()
                .postId(post.getId().getValue())
                .accountId(post.getAccountId())
                .createdAt(post.getCreatedAt())
                .description(post.getDescription())
                .username(account.getUsername())
                .title(post.getTitle())
                .build();
    }
}
