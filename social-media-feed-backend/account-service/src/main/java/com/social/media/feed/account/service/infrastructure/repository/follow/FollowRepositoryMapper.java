package com.social.media.feed.account.service.infrastructure.repository.follow;

import com.social.media.feed.account.service.domain.entity.Follow;
import org.springframework.stereotype.Component;

@Component
public class FollowRepositoryMapper {

    public FollowEntity followToFollowEntity(Follow follow) {
        return FollowEntity.builder()
                .followId(follow.getId().getValue())
                .followerId(follow.getFollowerId())
                .followeeId(follow.getFolloweeId())
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
