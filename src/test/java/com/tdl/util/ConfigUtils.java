package com.tdl.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {

    private static Properties properties;

    // Static block to initialize the Properties object
    static {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file: " + e.getMessage());
        }
    }

    public static String getConfigProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null)
            throw new RuntimeException("Key not found in configuration file: " + key);

        return value;
    }
}
