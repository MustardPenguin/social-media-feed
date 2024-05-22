package com.social.media.feed.account.service.application.port.repository;

import com.social.media.feed.account.service.domain.entity.Follow;

import java.util.List;
import java.util.UUID;

public interface FollowRepository {

    Follow saveFollow(Follow follow);

    Follow findFollowByFollowerIdAndFolloweeId(Follow follow);

    List<Follow> getFollowersByAccountId(UUID accountId);

    List<Follow> getFolloweesByAccountId(UUID accountId);
}
