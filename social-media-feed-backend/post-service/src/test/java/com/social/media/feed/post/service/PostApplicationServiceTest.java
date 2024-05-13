package com.social.media.feed.post.service;

import com.social.media.feed.post.service.application.rest.controller.PostController;
import com.social.media.feed.post.service.application.rest.model.CreatePostRequestBody;
import com.social.media.feed.post.service.application.util.PostServiceUtil;
import com.social.media.feed.post.service.domain.entity.Account;
import com.social.media.feed.post.service.infrastructure.repository.outbox.postcreated.PostCreatedEventEntity;
import com.social.media.feed.post.service.infrastructure.repository.outbox.postcreated.PostCreatedEventJpaRepository;
import com.social.media.feed.post.service.infrastructure.repository.post.PostEntity;
import com.social.media.feed.post.service.infrastructure.repository.post.PostJpaRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(properties = "eureka.client.enabled=false")
public class PostApplicationServiceTest {

    @Autowired
    private PostController postController;
    @MockBean
    private PostServiceUtil postServiceUtil;
    @Autowired
    private PostJpaRepository postJpaRepository;
    @Autowired
    private PostCreatedEventJpaRepository postCreatedEventJpaRepository;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withUrlParam("stringtype", "unspecified")
            .withUrlParam("currentSchema", "post")
            .withInitScript("init-schema.sql")
            .withReuse(true);

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeAll
    public static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }



    @Test
    public void testPostService() {
        final UUID accountId = UUID.randomUUID();

        // Requires Account service so the class that requires it
        when(postServiceUtil.getAccountByAccountId(any(UUID.class))).thenReturn(Account.builder()
                .accountId(accountId)
                .username("test")
                .build());

        CreatePostRequestBody createPostRequestBody = new CreatePostRequestBody("My title", "My description");

        ResponseEntity<String> response = postController.createPost(createPostRequestBody, accountId);

        assertEquals(ResponseEntity.class, response.getClass());

        List<PostEntity> postEntities = postJpaRepository.findAll();
        assertEquals(1, postEntities.size());
        List<PostCreatedEventEntity> postCreatedEventEntities = postCreatedEventJpaRepository.findAll();
        assertEquals(1, postCreatedEventEntities.size());
    }
}
