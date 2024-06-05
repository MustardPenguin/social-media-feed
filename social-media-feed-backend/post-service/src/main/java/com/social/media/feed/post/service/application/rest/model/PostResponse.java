package com.social.media.feed.post.service.application.rest.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class PostResponse {

    private UUID postId;
    private UUID accountId;
    private String username;
    private String title;
    private String description;
    private LocalDateTime createdAt;
}
