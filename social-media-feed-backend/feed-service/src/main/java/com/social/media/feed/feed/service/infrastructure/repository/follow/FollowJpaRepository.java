package com.social.media.feed.feed.service.infrastructure.repository.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowJpaRepository extends JpaRepository<FollowEntity, UUID> {

    Optional<FollowEntity> findFollowEntityByFollowerIdAndAndFolloweeId(UUID followerId, UUID followeeId);

    void deleteFollowEntityByFollowerIdAndFolloweeId(UUID followerId, UUID followeeId);

    List<FollowEntity> findFollowEntitiesByFollowerId(UUID followerId);
}
