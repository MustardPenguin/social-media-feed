package com.social.media.feed.account.service.application.mapper;

import com.social.media.feed.account.service.application.rest.model.RegisterAccountRequestBody;
import com.social.media.feed.account.service.domain.entity.Account;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountServiceMapper {

    public Account registerAccountRequestBodyToAccount(RegisterAccountRequestBody registerAccountRequestBody) {
        return Account.builder()
                .accountId(UUID.randomUUID())
                .username(registerAccountRequestBody.getUsername())
                .password(registerAccountRequestBody.getPassword())
                .build();
    }
}
