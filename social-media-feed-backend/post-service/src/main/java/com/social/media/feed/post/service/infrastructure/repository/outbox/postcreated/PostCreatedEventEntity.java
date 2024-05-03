package com.social.media.feed.post.service.infrastructure.repository.outbox.postcreated;

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
@Table(name = "post_created_events")
public class PostCreatedEventEntity {

    @Id
    private UUID eventId;
    private LocalDateTime createdAt;
    private String payload;
}
