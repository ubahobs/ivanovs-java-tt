package com.tdl.googleMeet.util;

import com.tdl.util.ConfigUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import io.qameta.allure.testng.AllureTestNg;


@Listeners(AllureTestNg.class)
public class TestBase {

    @BeforeMethod
    public void setUp () {
        String browser1 = ConfigUtils.getConfigProperty("meet.user1.browser");
        String browser2 = ConfigUtils.getConfigProperty("meet.user2.browser");

        if (Boolean.parseBoolean(ConfigUtils.getConfigProperty("headless")))
            BrowserUtils.setHeadless();

        BrowserUtils.setChromeAudioVideoBrowserOptions();
        BrowserUtils.initializeDriver(browser1);

        BrowserUtils.setFirefoxAudioVideoBrowserOptions();
        BrowserUtils.initializeDriver(browser2);
    }

    @AfterMethod
    public void tearDown () {
        BrowserUtils.quitAllDrivers();
    }
}
