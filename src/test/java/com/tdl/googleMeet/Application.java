package com.tdl.googleMeet;

import com.tdl.googleMeet.pages.HomePage;
import com.tdl.googleMeet.util.BrowserUtils;
import com.tdl.util.ConfigUtils;

public class Application {

    public static HomePage navigateToHomePage (String browser) {
        BrowserUtils.open(browser, ConfigUtils.getConfigProperty("meet.url"));
        return new HomePage(browser);
    }
}
