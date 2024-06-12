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
    private final FollowCache followCache;

    public FollowRepositoryImpl(FollowRepositoryMapper followRepositoryMapper,
                                FollowJpaRepository followJpaRepository, FollowCache followCache) {
        this.followRepositoryMapper = followRepositoryMapper;
        this.followJpaRepository = followJpaRepository;
        this.followCache = followCache;
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
    public void deleteFollow(FollowCreatedEventModel followCreatedEventModel) {
        try {
            followJpaRepository.deleteFollowEntityByFollowerIdAndFolloweeId(followCreatedEventModel.getFollowerId(), followCreatedEventModel.getFolloweeId());
            followCache.deleteFollowFromCache(followCreatedEventModel.getFollowerId(), followCreatedEventModel.getFolloweeId());
        } catch (Exception e) {
            throw new FeedDomainException("Error occurred while deleting follow of id " + followCreatedEventModel.getFollowId(), e);
        }
    }

    @Override
    public Follow getFollowByFollowerIdAndFolloweeId(UUID followerId, UUID followeeId) {
        Follow follow = followCache.getFollowFromCache(followerId, followeeId);
        if(follow == null) {
            log.info("Follow not found in cache, fetching follow from database...");
            Optional<FollowEntity> response = followJpaRepository.findFollowEntityByFollowerIdAndAndFolloweeId(followerId, followeeId);
            if(response.isEmpty()) {
                return null;
            }
            follow = response.map(followRepositoryMapper::followEntityToFollow).get();
            followCache.saveFollowToCache(follow);
        }
        return follow;
    }
}
