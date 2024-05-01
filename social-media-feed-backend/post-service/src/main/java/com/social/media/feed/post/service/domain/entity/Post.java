package com.social.media.feed.post.service.domain.entity;

import com.social.media.feed.domain.entity.AggregateRoot;
import com.social.media.feed.post.service.domain.valueobject.PostId;

import java.time.LocalDateTime;
import java.util.UUID;

public class Post extends AggregateRoot<PostId> {

    private UUID accountId;
    private String title;
    private String description;
    private LocalDateTime createdAt;

    private Post(Builder builder) {
        super.setId(new PostId(builder.postId));
        accountId = builder.accountId;
        title = builder.title;
        description = builder.description;
        createdAt = builder.createdAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public void setPostId(UUID postId) {
        super.setId(new PostId(postId));
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID postId;
        private UUID accountId;
        private String title;
        private String description;
        private LocalDateTime createdAt;

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

        public Builder createdAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
