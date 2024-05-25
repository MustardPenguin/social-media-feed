package com.social.media.feed.account.service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Getter
@Builder
public class FollowWithUsername {

    private UUID accountId;
    private String username;

    public FollowWithUsername(UUID accountId, String username) {
        this.accountId = accountId;
        this.username = username;
    }
}
