package com.social.media.feed.account.service.infrastructure.repository.follow;

import com.social.media.feed.account.service.application.dto.FollowWithUsername;
import com.social.media.feed.account.service.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowJpaRepository extends JpaRepository<FollowEntity, UUID> {

    Optional<FollowEntity> findFollowEntityByFollowerIdAndAndFolloweeId(UUID followerId, UUID followeeId);

//    @Query("SELECT a.followId, a.followerId FROM FollowEntity a WHERE FollowEntity.followeeId = :followeeId")
    @Query(nativeQuery = true, value =
            "SELECT account.accounts.account_id, account.accounts.username FROM account.follows " +
                    "INNER JOIN account.accounts ON account.accounts.account_id = account.follows.follower_id " +
                    "WHERE account.follows.followee_id = :followeeId")
    List<Object[]> findFollowEntitiesByFolloweeId(UUID followeeId);

    List<FollowEntity> findFollowEntitiesByFollowerId(UUID followeeId);
}