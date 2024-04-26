package com.social.media.feed.post.service.application.util;

import com.social.media.feed.post.service.application.port.service.WebClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PostServiceUtil {

    @Value("${apiGatewayHost}")
    private String apiGatewayHost;
    private final WebClientService webClientService;

    public PostServiceUtil(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    public void validateAccount(UUID accountId) {
        String url = apiGatewayHost + "/api/account/" + accountId;
        String response = webClientService.getSynchronously(url);
        if(response.equals(accountId.toString())) {
            log.info("Found account!");
        }
    }
}
