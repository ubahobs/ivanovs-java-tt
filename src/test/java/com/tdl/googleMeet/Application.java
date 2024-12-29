package com.tdl.googleMeet;

import com.tdl.googleMeet.pages.HomePage;
import com.tdl.util.BrowserUtils;
import com.tdl.util.ConfigUtils;

public class Application {

    public static HomePage navigateToHomePage () {
        BrowserUtils.open(ConfigUtils.getConfigProperty("meet.url"));
        return new HomePage();
    }
}
