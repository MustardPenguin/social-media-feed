package com.social.media.feed.post.service.application.impl;

import com.social.media.feed.post.service.application.port.repository.PostRepository;
import com.social.media.feed.post.service.application.port.service.PostService;
import com.social.media.feed.post.service.application.util.PostServiceUtil;
import com.social.media.feed.post.service.domain.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostServiceUtil postServiceUtil;
    private final PostRepository postRepository;

    public PostServiceImpl(PostServiceUtil postServiceUtil, PostRepository postRepository) {
        this.postServiceUtil = postServiceUtil;
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        log.info("Creating post for account id {} at service layer", post.getAccountId());
        post.setPostId(UUID.randomUUID());
        postServiceUtil.getAccountResponseByAccountIdFromAccountService(post.getAccountId());
        return postRepository.save(post);
    }
}
