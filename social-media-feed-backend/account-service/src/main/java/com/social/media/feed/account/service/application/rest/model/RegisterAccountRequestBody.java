package com.social.media.feed.account.service.application.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterAccountRequestBody {

    private String username;
    private String password;
}
