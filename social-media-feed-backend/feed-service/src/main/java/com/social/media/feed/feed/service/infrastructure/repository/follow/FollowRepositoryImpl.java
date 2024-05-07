package com.social.media.feed.feed.service.infrastructure.repository.follow;

import com.social.media.feed.feed.service.application.dto.FollowCreatedEventModel;
import com.social.media.feed.feed.service.application.port.repository.FollowRepository;
import com.social.media.feed.feed.service.domain.exception.FeedDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
}
