package com.tdl.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {

    private static Properties properties;
    private static final String propsPath = "src/test/resources/test-config/project.properties";

    static {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(propsPath);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + e.getMessage());
        }
    }

    public static String getConfigProperty (String key) {
        String value = properties.getProperty(key);
        if (value == null)
            throw new RuntimeException("Key not found in configuration file: " + key);

        return value;
    }
}
