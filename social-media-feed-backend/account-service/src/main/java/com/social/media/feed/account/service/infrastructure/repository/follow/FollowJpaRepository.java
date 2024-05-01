package com.social.media.feed.account.service.infrastructure.repository.follow;

import com.social.media.feed.account.service.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowJpaRepository extends JpaRepository<FollowEntity, UUID> {

    Optional<FollowEntity> findFollowEntityByFollowerIdAndAndFolloweeId(UUID followerId, UUID followeeId);
}
