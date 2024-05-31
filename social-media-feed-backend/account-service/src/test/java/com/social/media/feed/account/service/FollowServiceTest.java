package com.social.media.feed.account.service;


import com.social.media.feed.account.service.application.dto.FollowWithUsername;
import com.social.media.feed.account.service.application.port.repository.AccountRepository;
import com.social.media.feed.account.service.application.port.service.AccountService;
import com.social.media.feed.account.service.application.rest.controller.AccountController;
import com.social.media.feed.account.service.application.rest.controller.FollowController;
import com.social.media.feed.account.service.application.rest.model.request.RegisterAccountRequestBody;
import com.social.media.feed.account.service.application.rest.model.response.FollowsResponse;
import com.social.media.feed.account.service.domain.entity.Account;
import com.social.media.feed.account.service.infrastructure.repository.outbox.followcreated.FollowCreatedEventEntity;
import com.social.media.feed.account.service.infrastructure.repository.outbox.followcreated.FollowCreatedEventJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = "eureka.client.enabled=false")
public class FollowServiceTest extends BaseTest {

    @Autowired
    private AccountController accountController;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private FollowController followController;
    @Autowired
    private FollowCreatedEventJpaRepository followCreatedEventJpaRepository;

    @Test
    public void testFollowService() {
        // Create accounts
        accountController.registerAccount(new RegisterAccountRequestBody("account1", "password"));
        accountController.registerAccount(new RegisterAccountRequestBody("account2", "password"));

        // Test get account by id from account service
        Account account1 = accountRepository.findAccountByUsername("account1");
        Account account2 = accountRepository.findAccountByUsername("account2");

        // Test follow service
        ResponseEntity<FollowWithUsername> response = followController.follow(account1.getId().getValue(), account2.getId().getValue());

        assertEquals(ResponseEntity.class, response.getClass());
        assertNotNull(response.getBody());
        assertEquals(account2.getUsername(), response.getBody().getUsername());

        // Test follow service with already following account
        try {
            followController.follow(account1.getId().getValue(), account2.getId().getValue());
        } catch (Exception e) {
            assertEquals("Already following the account with accountId " + account2.getId().getValue(), e.getMessage());
        }

        // Validate that a follow created event was created
        List<FollowCreatedEventEntity> followCreatedEventEntities = followCreatedEventJpaRepository.findAll();
        assertEquals(1, followCreatedEventEntities.size());

        // Test fetch followers/followees
        ResponseEntity<FollowsResponse> followersResponse = followController.getFollowers(account2.getId().getValue());
        assertNotNull(followersResponse.getBody());
        List<FollowWithUsername> followers = followersResponse.getBody().getFollows();
        assertEquals(account1.getId().getValue(), followers.get(0).getAccountId());
        assertEquals(account1.getUsername(), followers.get(0).getUsername());

        ResponseEntity<FollowsResponse> followeesResponse = followController.getFollowees(account1.getId().getValue());
        assertNotNull(followeesResponse.getBody());
        List<FollowWithUsername> followees = followeesResponse.getBody().getFollows();
        assertEquals(account2.getId().getValue(), followees.get(0).getAccountId());
        assertEquals(account2.getUsername(), followees.get(0).getUsername());

        // Test unfollow
        ResponseEntity<FollowWithUsername> unfollowResponse = followController.unfollow(account1.getId().getValue(), account2.getId().getValue());
        assertNotNull(unfollowResponse.getBody());
        assertEquals(account2.getUsername(), unfollowResponse.getBody().getUsername());

        // Test unfollow with already unfollowed account
        try {
            followController.unfollow(account1.getId().getValue(), account2.getId().getValue());
        } catch (Exception e) {
            assertEquals("Not following the account with accountId " + account2.getId().getValue() + "!", e.getMessage());
        }

        followersResponse = followController.getFollowers(account2.getId().getValue());
        assertNotNull(followersResponse.getBody());
        followers = followersResponse.getBody().getFollows();
        assertEquals(0, followers.size());
    }
}
