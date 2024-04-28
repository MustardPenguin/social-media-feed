package com.social.media.feed.application.rest.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.media.feed.domain.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ObjectMapperUtil {

    private final ObjectMapper objectMapper;

    public ObjectMapperUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T convertStringToObject(String jsonString, Class<T> classType) {
        try {
            return objectMapper.readValue(jsonString, classType);
        } catch (Exception e) {
            log.error("Error occurred while converting string to object of type {}", classType.getName(), e);
            throw new DomainException("Error occurred while converting string to object of type " + classType.getName() + ", error: " + e.getMessage());
        }
    }
}
