package com.social.media.feed.account.service.infrastructure.repository.follow;

import com.social.media.feed.account.service.application.dto.FollowWithUsername;
import com.social.media.feed.account.service.application.port.repository.FollowRepository;
import com.social.media.feed.account.service.domain.entity.Follow;
import com.social.media.feed.account.service.domain.exception.AccountDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class FollowRepositoryImpl implements FollowRepository {

    private final FollowRepositoryMapper followRepositoryMapper;
    private final FollowJpaRepository followJpaRepository;

    public FollowRepositoryImpl(FollowRepositoryMapper followRepositoryMapper, FollowJpaRepository followJpaRepository) {
        this.followRepositoryMapper = followRepositoryMapper;
        this.followJpaRepository = followJpaRepository;
    }

    @Override
    public Follow saveFollow(Follow follow) {
        try {
            FollowEntity followEntity = followRepositoryMapper.followToFollowEntity(follow);
            FollowEntity response = followJpaRepository.save(followEntity);
            return followRepositoryMapper.followEntityToFollow(response);
        } catch (Exception e) {
            log.error("Error while saving follow for follower {} and followee {}", follow.getFollowerId(), follow.getFolloweeId(), e);
            throw new AccountDomainException("Error while saving follow for follower " + follow.getFollowerId() + " and followee " + follow.getFolloweeId(), e);
        }
    }

    @Override
    public void deleteFollow(Follow follow) {
        try {
            followJpaRepository.deleteFollowEntityByFollowerIdAndAndFolloweeId(follow.getFollowerId(), follow.getFolloweeId());
        } catch (Exception e) {
            log.error("Error while deleting follow for follower {} and followee {}", follow.getFollowerId(), follow.getFolloweeId(), e);
            throw new AccountDomainException("Error while deleting follow for follower " + follow.getFollowerId() + " and followee " + follow.getFolloweeId(), e);
        }
    }

    @Override
    public Follow findFollowByFollowerIdAndFolloweeId(Follow follow) {
        FollowEntity followEntity = followRepositoryMapper.followToFollowEntity(follow);
        Optional<FollowEntity> response = followJpaRepository.findFollowEntityByFollowerIdAndAndFolloweeId(followEntity.getFollowerId(), followEntity.getFolloweeId());
        return response.map(followRepositoryMapper::followEntityToFollow).orElse(null);
    }

    @Override
    public List<FollowWithUsername> getFollowersByAccountId(UUID accountId) {
        List<FollowWithUsername> followsWithUsername = new ArrayList<>();
        List<Object[]> result = followJpaRepository.findFollowEntitiesByFolloweeId(accountId);
        for(Object[] follow : result) {
            FollowWithUsername followWithUsername = new FollowWithUsername((UUID) follow[0], (String) follow[1]);
            followsWithUsername.add(followWithUsername);
        }
        return followsWithUsername;
    }

    @Override
    public List<FollowWithUsername> getFolloweesByAccountId(UUID accountId) {
        List<FollowWithUsername> followsWithUsername = new ArrayList<>();
        List<Object[]> result = followJpaRepository.findFollowEntitiesByFollowerId(accountId);
        for(Object[] follow : result) {
            FollowWithUsername followWithUsername = new FollowWithUsername((UUID) follow[0], (String) follow[1]);
            followsWithUsername.add(followWithUsername);
        }
        return followsWithUsername;
    }
}
