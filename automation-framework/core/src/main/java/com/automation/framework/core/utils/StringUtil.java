package com.automation.framework.core.utils;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StringUtil {
    
    public static String toAbsolutePath(String relativePath) {
        if (relativePath == null || relativePath.isEmpty()) {
            throw new IllegalArgumentException("Relative path cannot be null or empty");
        }
        Path path = Paths.get(relativePath).toAbsolutePath().normalize();
        return path.toString();
    }
}
