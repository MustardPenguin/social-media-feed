package com.social.media.feed.account.service.application.port.service;

import com.social.media.feed.account.service.domain.entity.Follow;

import java.util.List;
import java.util.UUID;

public interface FollowService {

    Follow followAccount(UUID followerId, UUID followeeId);

    List<Follow> getFollowersByAccountId(UUID accountId);

    List<Follow> getFolloweesByAccountId(UUID accountId);
}
