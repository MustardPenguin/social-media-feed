package com.social.media.feed.feed.service.domain.entity;

import java.io.Serializable;
import java.util.UUID;

public class Follow implements Serializable {

    private UUID followId;
    private UUID followerId;
    private UUID followeeId;

    private Follow(Builder builder) {
        followId = builder.followId;
        followerId = builder.followerId;
        followeeId = builder.followeeId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getFollowId() {
        return followId;
    }

    public UUID getFollowerId() {
        return followerId;
    }

    public UUID getFolloweeId() {
        return followeeId;
    }


    public static final class Builder {
        private UUID followId;
        private UUID followerId;
        private UUID followeeId;

        private Builder() {
        }

        public Builder followId(UUID val) {
            followId = val;
            return this;
        }

        public Builder followerId(UUID val) {
            followerId = val;
            return this;
        }

        public Builder followeeId(UUID val) {
            followeeId = val;
            return this;
        }

        public Follow build() {
            return new Follow(this);
        }
    }
}
