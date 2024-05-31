package com.social.media.feed.account.service.application.port.repository;

import com.social.media.feed.account.service.application.dto.FollowWithUsername;
import com.social.media.feed.account.service.domain.entity.Follow;

import java.util.List;
import java.util.UUID;

public interface FollowRepository {

    Follow saveFollow(Follow follow);

    void deleteFollow(Follow follow);

    Follow findFollowByFollowerIdAndFolloweeId(Follow follow);

    List<FollowWithUsername> getFollowersByAccountId(UUID accountId);

    List<FollowWithUsername> getFolloweesByAccountId(UUID accountId);
}
