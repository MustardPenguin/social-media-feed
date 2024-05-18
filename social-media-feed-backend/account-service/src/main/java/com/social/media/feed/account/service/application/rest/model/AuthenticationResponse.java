package com.social.media.feed.account.service.application.rest.model;

import com.social.media.feed.application.rest.model.HttpResponse;
import lombok.Getter;

@Getter
public class AuthenticationResponse extends HttpResponse {

    private final String token;

    public AuthenticationResponse(String message, String token) {
        super(message);
        this.token = token;
    }
}
