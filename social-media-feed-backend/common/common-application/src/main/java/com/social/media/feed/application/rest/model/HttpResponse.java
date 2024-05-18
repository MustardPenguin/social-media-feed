package com.social.media.feed.application.rest.model;

import lombok.Getter;

@Getter
public class HttpResponse {

    private final String message;

    public HttpResponse(String message) {
        this.message = message;
    }
}
