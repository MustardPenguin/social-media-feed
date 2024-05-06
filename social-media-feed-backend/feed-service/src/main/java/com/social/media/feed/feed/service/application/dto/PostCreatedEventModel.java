package com.social.media.feed.feed.service.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class PostCreatedEventModel {

    private UUID accountId;
    private UUID postId;
    private LocalDateTime createdAt;
}
