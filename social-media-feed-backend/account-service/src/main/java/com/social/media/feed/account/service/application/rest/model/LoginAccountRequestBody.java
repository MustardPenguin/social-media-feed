package com.social.media.feed.account.service.application.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginAccountRequestBody {

    private String username;
    private String password;
}
