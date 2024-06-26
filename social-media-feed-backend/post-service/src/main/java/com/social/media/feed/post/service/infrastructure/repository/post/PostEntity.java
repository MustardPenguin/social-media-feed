package com.social.media.feed.post.service.infrastructure.repository.post;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class PostEntity implements Serializable {

    @Id
    private UUID postId;
    private UUID accountId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
}
