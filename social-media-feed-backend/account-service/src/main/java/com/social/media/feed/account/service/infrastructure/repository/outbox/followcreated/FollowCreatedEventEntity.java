package com.social.media.feed.account.service.infrastructure.repository.outbox.followcreated;

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
@Table(name = "follow_created_events")
public class FollowCreatedEventEntity {

    @Id
    private UUID eventId;
    private String payload;
    private LocalDateTime createdAt;
}
