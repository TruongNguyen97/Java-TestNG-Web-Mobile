package com.automation.framework.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtil {

    public static <T> Map<String, T> readJsonFileToObject(String filePath, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        filePath = StringUtil.toAbsolutePath(filePath);
        try {
            
            Map<String, T> data = objectMapper.readValue(
                new File(filePath),
                objectMapper.getTypeFactory().constructMapType(Map.class, String.class, clazz)
            );

            return data;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }
}
