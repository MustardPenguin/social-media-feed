package com.social.media.feed.post.service.application.util;

import com.social.media.feed.application.rest.model.AccountResponse;
import com.social.media.feed.application.rest.util.ObjectMapperUtil;
import com.social.media.feed.post.service.domain.exception.PostDomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PostServiceUtil {

    @Value("${apiGatewayHost}")
    private String apiGatewayHost;

    private final ObjectMapperUtil objectMapperUtil;
    private final PostFeignClient postFeignClient;

    public PostServiceUtil(ObjectMapperUtil objectMapperUtil, PostFeignClient postFeignClient) {
        this.objectMapperUtil = objectMapperUtil;
        this.postFeignClient = postFeignClient;
    }

    public void validateAccount(UUID accountId) {
        String response = postFeignClient.getAccountByAccountId(accountId);
        log.info("Found account of username " + response + " for account id " + accountId + "!");
        if(response == null || response.isEmpty()) {
            throw new PostDomainException("Account not found for account id " + accountId + " in account service!");
        }
        AccountResponse accountResponse = objectMapperUtil.convertStringToObject(response, AccountResponse.class);
        
    }
}
