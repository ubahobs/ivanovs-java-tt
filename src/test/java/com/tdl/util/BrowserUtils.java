package com.tdl.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserUtils {
    private static WebDriver driver;

    /**
     * Gets the WebDriver instance.
     * @return The WebDriver instance.
     */
    public static WebDriver getDriver() {
        if (driver == null)
            throw new RuntimeException("WebDriver is not initialized. Call initializeDriver() first.");

        return driver;
    }

    /**
     * Initializes the WebDriver if not already initialized.
     * @param browser The browser to use (e.g., "chrome", "firefox").
     */
    public static void initializeDriver(String browser) {
        if (driver == null)
            switch (browser.toLowerCase()) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
        driver.manage().window().maximize();
    }

    /**
     * Opens the specified URL in the browser.
     * @param url The URL to open.
     */
    public static void open(String url) {
        if (driver == null)
            throw new RuntimeException("WebDriver is not initialized. Call initializeDriver() first.");

        driver.get(url);
    }

    /**
     * Closes the browser and quits the WebDriver.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
