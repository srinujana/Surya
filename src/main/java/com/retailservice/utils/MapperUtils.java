package com.retailservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Surya on 06/08/2019.
 */
@Component
public class MapperUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperUtils.class);

    public String getObjectAsJSON(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try {
            str = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            LOGGER.error("getObjectAsJSON() - Exception Occurred: {}", ex.getMessage());
        }
        return str;
    }
}
