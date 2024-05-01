package com.social.media.feed.account.service.domain.entity;

import com.social.media.feed.domain.entity.AggregateRoot;
import com.social.media.feed.domain.valueobject.FollowId;

import java.util.UUID;

public class Follow extends AggregateRoot<FollowId> {

    private UUID followerId;
    private UUID followeeId;

    private Follow(Builder builder) {
        super.setId(new FollowId(builder.followId));
        followerId = builder.followerId;
        followeeId = builder.followeeId;
    }

    public static Builder builder() {
        return new Builder();
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
