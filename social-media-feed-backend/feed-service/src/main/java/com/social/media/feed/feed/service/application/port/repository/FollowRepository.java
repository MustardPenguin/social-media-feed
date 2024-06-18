package com.social.media.feed.feed.service.application.port.repository;


import com.social.media.feed.feed.service.application.dto.FollowCreatedEventModel;
import com.social.media.feed.feed.service.domain.entity.Follow;

import java.util.List;
import java.util.UUID;

public interface FollowRepository {

    FollowCreatedEventModel saveFollow(FollowCreatedEventModel followCreatedEventModel);

    void deleteFollow(FollowCreatedEventModel followCreatedEventModel);

    Follow getFollowByFollowerIdAndFolloweeId(UUID followerId, UUID followeeId);

    List<Follow> getFolloweesByFollowerId(UUID followerId);
}
