package com.social.media.feed.account.service.application.rest.model.response;

import com.social.media.feed.account.service.application.dto.FollowWithUsername;
import com.social.media.feed.account.service.domain.entity.Follow;
import com.social.media.feed.application.rest.model.HttpResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class FollowsResponse extends HttpResponse {

    private final List<FollowWithUsername> follows;

    public FollowsResponse(String message, List<FollowWithUsername> follows) {
        super(message);
        this.follows = follows;
    }
}
