package com.tdl.googleMeet;

import com.tdl.googleMeet.pages.LandingPage;
import com.tdl.util.BrowserUtils;
import com.tdl.util.ConfigUtils;

public class Application {
    public static LandingPage navigateToLandingPage () {
        BrowserUtils.open(ConfigUtils.getConfigProperty("meet.url"));
        return new LandingPage();
    }
}
