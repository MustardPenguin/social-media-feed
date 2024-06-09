package com.social.media.feed.post.service.infrastructure.repository.post;

import com.social.media.feed.post.service.application.port.repository.PostRepository;
import com.social.media.feed.post.service.domain.entity.Account;
import com.social.media.feed.post.service.domain.entity.Post;
import com.social.media.feed.post.service.domain.exception.PostDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class PostRepositoryImpl implements PostRepository {

    private final PostRepositoryMapper postRepositoryMapper;
    private final PostJpaRepository postJpaRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public PostRepositoryImpl(PostRepositoryMapper postRepositoryMapper,
                              PostJpaRepository postJpaRepository,
                              RedisTemplate<String, Object> redisTemplate) {
        this.postRepositoryMapper = postRepositoryMapper;
        this.postJpaRepository = postJpaRepository;
        this.redisTemplate = redisTemplate;
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

    @Override
    public Post getPostById(UUID postId) {
        Post post = getPostByIdFromCache(postId);
        if(post == null) {
            Optional<PostEntity> postEntity = postJpaRepository.findPostEntityByPostId(postId);
            post = postEntity.map(postRepositoryMapper::postEntityToPost).orElse(null);
            if(post != null) {
                savePostToCache(postEntity.get());
            }
        }
        return post;
    }

    private Post getPostByIdFromCache(UUID postId) {
        PostEntity postEntity = (PostEntity) redisTemplate.opsForValue().get(postId.toString());
        if(postEntity == null) {
            log.info("Post not found in cache for id {}", postId);
            return null;
        }
        return postRepositoryMapper.postEntityToPost(postEntity);
    }

    private void savePostToCache(PostEntity postEntity) {
        String key = postEntity.getPostId().toString();
        redisTemplate.opsForValue().set(key, postEntity);
        redisTemplate.expire(key, 600, java.util.concurrent.TimeUnit.SECONDS);
    }
}
