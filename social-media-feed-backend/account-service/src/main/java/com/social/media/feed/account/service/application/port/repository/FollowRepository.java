package com.social.media.feed.account.service.application.port.repository;

import com.social.media.feed.account.service.domain.entity.Follow;

public interface FollowRepository {

    Follow saveFollow(Follow follow);

    Follow findFollowByFollowerIdAndFolloweeId(Follow follow);
}
