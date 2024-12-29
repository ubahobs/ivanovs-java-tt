package com.tdl.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigUtils class provides utility methods for reading and accessing configuration
 * properties from a specified properties file.
 */
public class ConfigUtils {

    private static final Properties properties;
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

    /**
     * Retrieves the value associated with the specified key from the properties file.
     *
     * @param key The key whose value needs to be fetched from the configuration file.
     * @return The value associated with the given key.
     * @throws RuntimeException if the key is not found in the configuration file.
     */
    public static String getConfigProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key not found in configuration file: " + key);
        }
        return value;
    }
}
