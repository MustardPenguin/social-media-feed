package com.social.media.feed.infrastructure.message.dto.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreatedEventPayload {

    @JsonProperty
    private UUID postId;
    @JsonProperty
    private UUID accountId;
}
