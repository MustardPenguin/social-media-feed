package com.social.media.feed.feed.service.infrastructure.repository.post;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class PostEntity {

    @Id
    private UUID postId;
    private UUID accountId;
    private LocalDateTime createdAt;
}
