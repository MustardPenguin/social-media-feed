package com.social.media.feed.infrastructure.message;

import org.apache.avro.specific.SpecificRecordBase;

import java.util.List;

public interface KafkaConsumer<T extends SpecificRecordBase> {

    void receiveMessages(List<T> messages);
}
