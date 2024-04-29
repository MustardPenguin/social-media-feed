package com.social.media.feed.post.service.domain.entity;

import java.io.Serializable;
import java.util.UUID;

public class Account implements Serializable {

    private UUID accountId;
    private String username;

    public Account() {}

    private Account(Builder builder) {
        accountId = builder.accountId;
        username = builder.username;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }


    public static final class Builder {
        private UUID accountId;
        private String username;

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

        public Account build() {
            return new Account(this);
        }
    }
}
