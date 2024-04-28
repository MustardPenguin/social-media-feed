package com.social.media.feed.account.service.application.mapper;

import com.social.media.feed.account.service.application.rest.model.LoginAccountRequestBody;
import com.social.media.feed.account.service.application.rest.model.RegisterAccountRequestBody;
import com.social.media.feed.account.service.domain.entity.Account;
import com.social.media.feed.application.rest.model.AccountResponse;
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

    public Account loginAccountRequestBodyToAccount(LoginAccountRequestBody loginAccountRequestBody) {
        return Account.builder()
                .username(loginAccountRequestBody.getUsername())
                .password(loginAccountRequestBody.getPassword())
                .build();
    }

    public AccountResponse accountToAccountResponse(Account account) {
        return AccountResponse.builder()
                .accountId(account.getId().getValue())
                .username(account.getUsername())
                .build();
    }
}
