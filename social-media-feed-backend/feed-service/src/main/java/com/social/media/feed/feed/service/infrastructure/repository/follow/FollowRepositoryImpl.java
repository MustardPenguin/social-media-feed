package com.social.media.feed.feed.service.infrastructure.repository.follow;

import com.social.media.feed.feed.service.application.dto.FollowCreatedEventModel;
import com.social.media.feed.feed.service.application.port.repository.FollowRepository;
import com.social.media.feed.feed.service.domain.entity.Follow;
import com.social.media.feed.feed.service.domain.exception.FeedDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class FollowRepositoryImpl implements FollowRepository {

    private final FollowRepositoryMapper followRepositoryMapper;
    private final FollowJpaRepository followJpaRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public FollowRepositoryImpl(FollowRepositoryMapper followRepositoryMapper,
                                FollowJpaRepository followJpaRepository,
                                RedisTemplate<String, Object> redisTemplate) {
        this.followRepositoryMapper = followRepositoryMapper;
        this.followJpaRepository = followJpaRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public FollowCreatedEventModel saveFollow(FollowCreatedEventModel followCreatedEventModel) {
        try {
            FollowEntity followEntity = followRepositoryMapper.followCreatedEventToFollowEntity(followCreatedEventModel);
            FollowEntity response = followJpaRepository.save(followEntity);
            return followRepositoryMapper.followEntityToFollowCreatedEvent(response);
        } catch (Exception e) {
            log.error("Error occurred while saving follow of id {}, error {}", followCreatedEventModel.getFollowId(), e.getMessage(), e);
            throw new FeedDomainException("Error occurred while saving follow of id " + followCreatedEventModel.getFollowId(), e);
        }
    }

    @Override
    public Follow getFollowByFollowerIdAndFolloweeId(UUID followerId, UUID followeeId) {
        Follow follow = getFollowByFollowerIdAndFolloweeIdFromCache(followerId, followeeId);
        if(follow == null) {
            log.info("Follow not found in cache, fetching follow from database...");
            Optional<FollowEntity> response = followJpaRepository.findFollowEntityByFollowerIdAndAndFolloweeId(followerId, followeeId);
            if(response.isEmpty()) {
                return null;
            }
            follow = response.map(followRepositoryMapper::followEntityToFollow).get();
            saveFollowToCache(follow);
        }
        return follow;
    }

    private void saveFollowToCache(Follow follow) {
        String key = follow.getFollowerId() + "." + follow.getFolloweeId();
        redisTemplate.opsForValue().set(key, follow);
        redisTemplate.expire(key, 600, java.util.concurrent.TimeUnit.SECONDS);
    }

    private Follow getFollowByFollowerIdAndFolloweeIdFromCache(UUID followerId, UUID followeeId) {
        return (Follow) redisTemplate.opsForValue().get(followerId + "." + followeeId);
    }
}
