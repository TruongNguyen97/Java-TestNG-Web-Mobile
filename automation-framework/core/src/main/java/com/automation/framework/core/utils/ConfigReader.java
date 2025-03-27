package com.automation.framework.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = null;

    public static void initConfigReader(String filePath) throws IOException {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream fis = new FileInputStream(filePath)) {
                properties.load(fis);
            }
        }
        
    }

    public static String getProperty(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            return systemValue;
        }

        if (properties != null) {
            return properties.getProperty(key);
        }
        return null;
    }

    public static Properties getProperties() {
        return properties;
    }


}
