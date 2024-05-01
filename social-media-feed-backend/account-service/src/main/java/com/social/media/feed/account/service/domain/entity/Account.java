package com.social.media.feed.account.service.domain.entity;

import com.social.media.feed.account.service.domain.valueobject.AccountId;
import com.social.media.feed.domain.entity.AggregateRoot;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

public class Account extends AggregateRoot<AccountId> {

    private final String username;
    private String password;

    private Account(Builder builder) {
        super.setId(new AccountId(builder.accountId));
        username = builder.username;
        password = builder.password;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setId(UUID id) {
        super.setId(new AccountId(id));
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static final class Builder {
        private UUID accountId;
        private String username;
        private String password;

        private Builder() {
        }

        public Builder accountId(UUID val) {
            accountId = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
