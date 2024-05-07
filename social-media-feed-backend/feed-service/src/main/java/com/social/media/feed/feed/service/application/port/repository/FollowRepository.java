package com.social.media.feed.feed.service.application.port.repository;


import com.social.media.feed.feed.service.application.dto.FollowCreatedEventModel;

public interface FollowRepository {

    FollowCreatedEventModel saveFollow(FollowCreatedEventModel followCreatedEventModel);
}
