package com.social.media.feed.account.service.infrastructure.repository.outbox.followcreated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FollowCreatedEventJpaRepository extends JpaRepository<FollowCreatedEventEntity, UUID> {
}
