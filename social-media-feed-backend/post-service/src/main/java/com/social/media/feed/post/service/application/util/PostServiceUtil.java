package com.social.media.feed.post.service.application.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PostServiceUtil {

    @Value("${apiGatewayHost}")
    private String apiGatewayHost;

    private final PostFeignClient postFeignClient;

    public PostServiceUtil(PostFeignClient postFeignClient) {
        this.postFeignClient = postFeignClient;
    }

    public void validateAccount(UUID accountId) {
        String url = apiGatewayHost + "/api/account/" + accountId;
        String response = postFeignClient.getAccountByAccountId(accountId);
        log.info("Found account of username " + response + " for account id " + accountId + "!");
//        String response = webClientService.getSynchronously(url);
//        if(response.isEmpty()) {
//            throw new PostDomainException("Account of id " + accountId + " not found!");
//        }
//        log.info("Found account of username " + response + " for account id " + accountId + "!");
    }
}
