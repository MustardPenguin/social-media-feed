package com.social.media.feed.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public class DomainEvent<T> {

    private final LocalDateTime createdAt;
    private final UUID eventId;
    private final T entity;

    public DomainEvent(UUID eventId, T entity, LocalDateTime createdAt) {
        this.eventId = eventId;
        this.entity = entity;
        this.createdAt = createdAt;
    }

    public DomainEvent(T event, LocalDateTime localDateTime) {
        this.entity = event;
        this.createdAt = localDateTime;
        this.eventId = UUID.randomUUID();
    }

    public T getEntity() {
        return entity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UUID getEventId() {
        return eventId;
    }
}
