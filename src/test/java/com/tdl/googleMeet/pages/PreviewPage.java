package com.tdl.googleMeet.pages;

import com.tdl.googleMeet.util.BasePage;
import org.openqa.selenium.By;

public class PreviewPage extends BasePage {

    private final String browser;

    private final By joinNowButton = By.xpath("//span[contains(text(), 'Join now')]");

    public PreviewPage (String browser) {
        super(browser);
        this.browser = browser;
        waitForVisibility(joinNowButton);
    }

    public MeetingPage joinMeeting () {
        click(joinNowButton);

        return new MeetingPage(browser);
    }
}
