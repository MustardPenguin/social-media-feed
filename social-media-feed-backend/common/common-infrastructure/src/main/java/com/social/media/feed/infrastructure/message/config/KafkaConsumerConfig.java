package com.social.media.feed.infrastructure.message.config;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.io.Serializable;
import java.util.HashMap;

@Configuration
public class KafkaConsumerConfig<K extends Serializable, V extends SpecificRecordBase> {

    private final KafkaDataConfig kafkaDataConfig;

    public KafkaConsumerConfig(KafkaDataConfig kafkaDataConfig) {
        this.kafkaDataConfig = kafkaDataConfig;
    }

    @Bean
    @ConditionalOnProperty(name = "kafka-config-data.bootstrap-server")
    public ConsumerFactory<K, V> consumerFactory() {
        HashMap<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaDataConfig.getBootstrapServer());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaDataConfig.getKeyDeserializer());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaDataConfig.getValueDeserializer());

        config.put(kafkaDataConfig.getSpecificAvroReaderKey(), kafkaDataConfig.getSpecificAvroReader());
        config.put(kafkaDataConfig.getSchemaRegistryUrlKey(), kafkaDataConfig.getSchemaRegistryUrl());
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    @ConditionalOnProperty(name = "kafka-config-data.bootstrap-server")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<K, V>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
