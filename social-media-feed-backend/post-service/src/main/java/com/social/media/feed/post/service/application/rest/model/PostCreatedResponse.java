package com.social.media.feed.post.service.application.rest.model;

import com.social.media.feed.application.rest.model.HttpResponse;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PostCreatedResponse extends HttpResponse {

    public UUID postId;

    public PostCreatedResponse(String message, UUID postId) {
        super(message);
        this.postId = postId;
    }
}
