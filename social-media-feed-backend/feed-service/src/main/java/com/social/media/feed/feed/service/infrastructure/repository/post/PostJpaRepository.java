package com.social.media.feed.feed.service.infrastructure.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, UUID> {

    List<PostEntity> findTop10ByAccountIdInOrderByCreatedAt(Collection<UUID> accountId);
}
