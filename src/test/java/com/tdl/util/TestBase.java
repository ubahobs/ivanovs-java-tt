package com.tdl.util;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    @BeforeMethod
    public void setUp () {
        String browser = ConfigUtils.getConfigProperty("meet.user1.browser");
        BrowserUtils.setAudioVideoBrowserOptions();
        BrowserUtils.initializeDriver(browser);
    }

    @AfterMethod
    public void tearDown () {
        BrowserUtils.quitDriver();
    }
}
