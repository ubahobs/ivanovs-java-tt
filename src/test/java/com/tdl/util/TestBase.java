package com.tdl.util;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    @BeforeMethod
    public void setUp() {
        // Read browser type from the configuration file
        String browser = ConfigUtils.getConfigProperty("browser");

        // Initialize the browser
        BrowserUtils.initializeDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        // Quit the browser after each test
        BrowserUtils.quitDriver();
    }
}
