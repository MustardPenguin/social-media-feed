package com.social.media.feed.post.service.infrastructure.repository.outbox.postcreated;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface PostCreatedEventJpaRepository extends JpaRepository<PostCreatedEventEntity, UUID>{
}
