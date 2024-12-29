package com.tdl.googleMeet;

import com.tdl.googleMeet.pages.HomePage;
import com.tdl.googleMeet.util.BrowserUtils;
import com.tdl.util.ConfigUtils;
import io.qameta.allure.Step;

public class Application {

    @Step("Navigating to the home page")
    public static HomePage navigateToHomePage (String browser) {
        BrowserUtils.open(browser, ConfigUtils.getConfigProperty("meet.url"));
        return new HomePage(browser);
    }
}
