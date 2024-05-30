package com.social.media.feed.account.service.application.port.service;

import com.social.media.feed.account.service.application.dto.FollowWithUsername;
import com.social.media.feed.account.service.domain.entity.Follow;

import java.util.List;
import java.util.UUID;

public interface FollowService {

//    Follow followAccount(UUID followerId, UUID followeeId);

    FollowWithUsername followAccountWithUUID(UUID followerId, UUID followeeId);

    FollowWithUsername followAccountWithUsername(UUID followerId, String followeeUsername);

    List<FollowWithUsername> getFollowersByAccountId(UUID accountId);

    List<FollowWithUsername> getFolloweesByAccountId(UUID accountId);
}
