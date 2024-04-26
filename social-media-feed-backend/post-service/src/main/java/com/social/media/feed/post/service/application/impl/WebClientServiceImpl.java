package com.social.media.feed.post.service.application.impl;

import com.social.media.feed.post.service.application.configuration.WebClientConfig;
import com.social.media.feed.post.service.application.port.service.WebClientService;
import com.social.media.feed.post.service.domain.exception.PostDomainException;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WebClientServiceImpl implements WebClientService {

    private final WebClient webClient;

    public WebClientServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String getSynchronously(String url) {
        log.info("Sending post request to {}", url);
        String response = "";
        try {
            WebClient.ResponseSpec spec = webClient.get()
                    .uri(url)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .retrieve();
            response = spec.bodyToMono(String.class).block();
        } catch (Exception e) {
            response = e.getMessage();
            log.error("Error while sending post request: {}", response);
            throw new PostDomainException("Error while sending post request! Error " + e.getMessage(), e);
        } finally {
            log.info("Response: {}", response);
        }
        return response;
    }
}
