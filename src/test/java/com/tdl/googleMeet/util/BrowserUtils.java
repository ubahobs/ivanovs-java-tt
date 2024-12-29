package com.tdl.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.HashMap;
import java.util.Map;

public class BrowserUtils {
    private static final Map<String, WebDriver> drivers = new HashMap<>();
    private static final ChromeOptions chromeOptions = new ChromeOptions();
    private static final FirefoxOptions firefoxOptions = new FirefoxOptions();

    /**
     * Gets the WebDriver instance.
     * @return The WebDriver instance.
     */
    public static WebDriver getDriver (String driverName) {
        if (!drivers.containsKey(driverName))
            throw new RuntimeException("No WebDriver instance found with name: " + driverName);

        return drivers.get(driverName);
    }

    /**
     * Initializes the WebDriver if not already initialized.
     * @param driverName The browser to use (e.g., "chrome", "firefox").
     */
    public static void initializeDriver (String driverName) {
        WebDriver driver;
        if (!drivers.containsKey(driverName)) {
            switch (driverName.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", ConfigUtils.getConfigProperty("chrome.driver.path"));
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", ConfigUtils.getConfigProperty("gecko.driver.path"));
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + driverName);
            }
            driver.manage().window().maximize();
            drivers.put(driverName, driver);
        }
    }

    /**
     * Opens the specified URL in the browser.
     * @param url The URL to open.
     */
    public static void open (String driverName, String url) {
        WebDriver driver = drivers.get(driverName);

        if (driver == null)
            throw new RuntimeException("WebDriver is not initialized. Call initializeDriver() first.");

        driver.get(url);
    }

    /**
     * Closes all active WebDriver instances.
     */
    public static void quitAllDrivers() {
        for (WebDriver driver : drivers.values())
            if (driver != null) driver.quit();

        drivers.clear();
    }

    /**
     * Sets chrome audio / video options on.
     */
    public static void setChromeAudioVideoBrowserOptions () {
        chromeOptions
                .addArguments("--use-fake-ui-for-media-stream")
                .addArguments("--disable-media-stream-popup")
                .setExperimentalOption("prefs", Map.of(
                "profile.default_content_setting_values.media_stream_mic", 1,
                "profile.default_content_setting_values.media_stream_camera", 1
        ));

    }

    /**
     * Sets firefox driver audio / video options on.
     */
    public static void setFirefoxAudioVideoBrowserOptions () {
        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("media.navigator.permission.disabled", true);
        profile.setPreference("media.autoplay.default", 0);
        profile.setPreference("media.navigator.streams.fake", true);

        firefoxOptions.setProfile(profile);
    }
}
