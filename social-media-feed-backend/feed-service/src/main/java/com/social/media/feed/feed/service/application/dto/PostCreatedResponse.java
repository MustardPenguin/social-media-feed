package com.social.media.feed.feed.service.application.dto;

import java.util.UUID;

public record PostCreatedResponse(UUID postId, UUID accountId) {
}
