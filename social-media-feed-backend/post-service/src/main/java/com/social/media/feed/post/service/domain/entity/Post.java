package com.social.media.feed.post.service.domain.entity;

import com.social.media.feed.domain.entity.AggregateRoot;
import com.social.media.feed.post.service.domain.valueobject.PostId;

import java.util.UUID;

public class Post extends AggregateRoot<PostId> {

    private UUID accountId;
    private String title;
    private String description;

    private Post(Builder builder) {
        super.setId(new PostId(builder.postId));
        accountId = builder.accountId;
        title = builder.title;
        description = builder.description;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public void setPostId(UUID postId) {
        super.setId(new PostId(postId));
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID postId;
        private UUID accountId;
        private String title;
        private String description;

        private Builder() {
        }

        public Builder postId(UUID val) {
            postId = val;
            return this;
        }

        public Builder accountId(UUID val) {
            accountId = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
