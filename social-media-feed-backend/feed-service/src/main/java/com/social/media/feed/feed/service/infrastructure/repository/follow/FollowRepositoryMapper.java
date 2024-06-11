package com.social.media.feed.feed.service.infrastructure.repository.follow;

import com.social.media.feed.feed.service.application.dto.FollowCreatedEventModel;
import com.social.media.feed.feed.service.domain.entity.Follow;
import org.springframework.stereotype.Component;

@Component
public class FollowRepositoryMapper {

    public FollowEntity followCreatedEventToFollowEntity(FollowCreatedEventModel followCreatedEventModel) {
        return FollowEntity.builder()
                .followId(followCreatedEventModel.getFollowId())
                .followerId(followCreatedEventModel.getFollowerId())
                .followeeId(followCreatedEventModel.getFolloweeId())
                .build();
    }

    public FollowCreatedEventModel followEntityToFollowCreatedEvent(FollowEntity followEntity) {
        return FollowCreatedEventModel.builder()
                .followId(followEntity.getFollowId())
                .followerId(followEntity.getFollowerId())
                .followeeId(followEntity.getFolloweeId())
                .build();
    }

    public Follow followEntityToFollow(FollowEntity followEntity) {
        return Follow.builder()
                .followId(followEntity.getFollowId())
                .followerId(followEntity.getFollowerId())
                .followeeId(followEntity.getFolloweeId())
                .build();
    }
}
