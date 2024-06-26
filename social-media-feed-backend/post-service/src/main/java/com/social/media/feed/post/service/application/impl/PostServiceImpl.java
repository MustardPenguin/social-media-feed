package com.social.media.feed.post.service.application.impl;

import com.social.media.feed.post.service.application.port.repository.AccountRepository;
import com.social.media.feed.post.service.application.port.repository.PostCreatedEventRepository;
import com.social.media.feed.post.service.application.port.repository.PostRepository;
import com.social.media.feed.post.service.application.port.service.PostService;
import com.social.media.feed.post.service.domain.entity.Account;
import com.social.media.feed.post.service.domain.entity.Post;
import com.social.media.feed.post.service.domain.event.PostCreatedEvent;
import com.social.media.feed.post.service.domain.exception.PostDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostCreatedEventRepository postCreatedEventRepository;
    private final AccountRepository accountRepository;
    private final PostRepository postRepository;

    public PostServiceImpl(PostCreatedEventRepository postCreatedEventRepository,
                           AccountRepository accountRepository,
                           PostRepository postRepository) {
        this.postCreatedEventRepository = postCreatedEventRepository;
        this.accountRepository = accountRepository;
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public Post createPost(Post post) {
        log.info("Creating post for account id {} at service layer", post.getAccountId());
        LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("UTC"));
        post.setPostId(UUID.randomUUID());
        post.setCreatedAt(createdAt);

        Account account = accountRepository.getAccountByAccountUUID(post.getAccountId());
        Post response = postRepository.save(post);
        PostCreatedEvent postCreatedEvent = new PostCreatedEvent(response, createdAt);
        postCreatedEventRepository.save(postCreatedEvent);

        log.info("Account found of id {} and username {}!", account.getAccountId(), account.getUsername());
        return response;
    }

    @Override
    public Post getPostById(UUID postId) {
        log.info("Getting post by id {} at service layer", postId);
        Post post = postRepository.getPostById(postId);
        if(post == null) {
            log.error("Post not found for id {}", postId);
            throw new PostDomainException("Post not found for id " + postId);
        }
        return post;
    }
}
