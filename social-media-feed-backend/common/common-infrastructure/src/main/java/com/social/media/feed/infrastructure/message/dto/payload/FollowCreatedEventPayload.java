package com.social.media.feed.infrastructure.message.dto.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowCreatedEventPayload {

    @JsonProperty
    private UUID followId;
    @JsonProperty
    private UUID followerId;
    @JsonProperty
    private UUID followeeId;
}
