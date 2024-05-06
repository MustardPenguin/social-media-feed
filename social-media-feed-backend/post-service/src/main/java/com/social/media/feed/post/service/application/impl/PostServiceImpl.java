package com.social.media.feed.post.service.application.impl;

import com.social.media.feed.post.service.application.port.repository.PostCreatedEventRepository;
import com.social.media.feed.post.service.application.port.repository.PostRepository;
import com.social.media.feed.post.service.application.port.service.PostService;
import com.social.media.feed.post.service.application.util.PostServiceUtil;
import com.social.media.feed.post.service.domain.entity.Account;
import com.social.media.feed.post.service.domain.entity.Post;
import com.social.media.feed.post.service.domain.event.PostCreatedEvent;
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
    private final PostServiceUtil postServiceUtil;
    private final PostRepository postRepository;

    public PostServiceImpl(PostCreatedEventRepository postCreatedEventRepository, PostServiceUtil postServiceUtil, PostRepository postRepository) {
        this.postCreatedEventRepository = postCreatedEventRepository;
        this.postServiceUtil = postServiceUtil;
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public Post createPost(Post post) {
        log.info("Creating post for account id {} at service layer", post.getAccountId());
        LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("UTC"));
        post.setPostId(UUID.randomUUID());
        post.setCreatedAt(createdAt);

        Account account = postServiceUtil.getAccountByAccountId(post.getAccountId());
        Post response = postRepository.save(post);
        PostCreatedEvent postCreatedEvent = new PostCreatedEvent(response, createdAt);
        postCreatedEventRepository.save(postCreatedEvent);

        log.info("Account found of id {} and username {}!", account.getAccountId(), account.getUsername());
        return response;
    }
}
