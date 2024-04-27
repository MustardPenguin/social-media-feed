package com.social.media.feed.post.service.application.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "postFeignClient", url = "${apiGatewayHost}")
public interface PostFeignClient {

    @GetMapping("/api/account/{accountId}")
    String getAccountByAccountId(@PathVariable UUID accountId);
}
