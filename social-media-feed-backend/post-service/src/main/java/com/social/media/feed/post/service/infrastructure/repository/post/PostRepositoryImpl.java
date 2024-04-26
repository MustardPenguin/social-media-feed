package com.social.media.feed.post.service.infrastructure.repository.post;

import com.social.media.feed.post.service.application.port.repository.PostRepository;
import com.social.media.feed.post.service.domain.entity.Post;
import com.social.media.feed.post.service.domain.exception.PostDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostRepositoryImpl implements PostRepository {

    private final PostRepositoryMapper postRepositoryMapper;
    private final PostJpaRepository postJpaRepository;

    public PostRepositoryImpl(PostRepositoryMapper postRepositoryMapper, PostJpaRepository postJpaRepository) {
        this.postRepositoryMapper = postRepositoryMapper;
        this.postJpaRepository = postJpaRepository;
    }

    @Override
    public Post save(Post post) {
        PostEntity postEntity = postRepositoryMapper.postToPostEntity(post);
        try {
            PostEntity response = postJpaRepository.save(postEntity);
            return postRepositoryMapper.postEntityToPost(response);
        } catch (Exception e) {
            log.error("Error while saving post for user {}, error: {}", post.getAccountId(), e.getMessage());
            throw new PostDomainException("Error while saving post for user " + post.getAccountId()
                    + ", error: " + e.getMessage(), e);
        }
    }
}
