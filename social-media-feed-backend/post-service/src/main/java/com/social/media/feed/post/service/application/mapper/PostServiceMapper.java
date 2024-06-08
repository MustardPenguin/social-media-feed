package com.social.media.feed.post.service.application.mapper;

import com.social.media.feed.post.service.application.port.repository.AccountRepository;
import com.social.media.feed.post.service.application.rest.model.CreatePostRequestBody;
import com.social.media.feed.post.service.application.rest.model.PostResponse;
import com.social.media.feed.post.service.domain.entity.Account;
import com.social.media.feed.post.service.domain.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostServiceMapper {

    private final AccountRepository accountRepository;

    public PostServiceMapper(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Post createPostRequestBodyToPost(CreatePostRequestBody createPostRequestBody) {
        return Post.builder()
                .title(createPostRequestBody.getTitle())
                .description(createPostRequestBody.getDescription())
                .build();
    }

    public PostResponse postToPostResponse(Post post) {
        Account account = accountRepository.getAccountByAccountUUID(post.getAccountId());
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
