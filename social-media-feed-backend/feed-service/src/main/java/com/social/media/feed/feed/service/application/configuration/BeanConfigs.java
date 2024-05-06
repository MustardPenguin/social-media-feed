package com.social.media.feed.feed.service.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Sinks;

@Configuration
public class BeanConfigs {

    @Bean
    public <T> Sinks.Many<T> eventSink() {
        return Sinks.many().multicast().directBestEffort();
    }
}
