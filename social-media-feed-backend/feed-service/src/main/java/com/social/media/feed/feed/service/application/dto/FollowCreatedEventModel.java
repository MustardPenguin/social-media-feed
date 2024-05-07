package com.social.media.feed.feed.service.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class FollowCreatedEventModel {

    private UUID followId;
    private UUID followerId;
    private UUID followeeId;
}
