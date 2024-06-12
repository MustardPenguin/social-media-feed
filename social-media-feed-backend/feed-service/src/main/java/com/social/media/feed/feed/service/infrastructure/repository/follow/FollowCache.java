package com.social.media.feed.feed.service.infrastructure.repository.follow;

import com.social.media.feed.feed.service.domain.entity.Follow;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FollowCache {

    private final RedisTemplate<String, Object> redisTemplate;


    public FollowCache(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveFollowToCache(Follow follow) {
        String key = follow.getFollowerId() + "." + follow.getFolloweeId();
        redisTemplate.opsForValue().set(key, follow);
        redisTemplate.expire(key, 600, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void deleteFollowFromCache(UUID followerId, UUID followeeId) {
        redisTemplate.delete(followerId + "." + followeeId);
    }

    public Follow getFollowFromCache(UUID followerId, UUID followeeId) {
        return (Follow) redisTemplate.opsForValue().get(followerId + "." + followeeId);
    }
}
