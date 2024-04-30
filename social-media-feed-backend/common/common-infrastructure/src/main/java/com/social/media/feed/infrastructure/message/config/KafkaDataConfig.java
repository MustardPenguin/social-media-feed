package com.social.media.feed.infrastructure.message.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "kafka-config-data")
public class KafkaDataConfig {

    private String bootstrapServer;
    private String schemaRegistryUrlKey;
    private String schemaRegistryUrl;
    private String keyDeserializer;
    private String valueDeserializer;
    private String specificAvroReaderKey;
    private String specificAvroReader;
}
