package com.tdl.googleMeet.pages;

import com.tdl.googleMeet.util.BasePage;
import org.openqa.selenium.By;

public class LandingPage extends BasePage {

    private final String browser;

    private final By newMeetingButton = By.xpath("//span[contains(text(), 'New meeting')]");
    private final By instantMeetingOption = By.cssSelector("[aria-label='Start an instant meeting']");
    private final By meetingLinkOrCodeInput = By.cssSelector("[placeholder='Enter a code or link']");
    private final By joinMeetingButton = By.xpath("//span[contains(text(), 'Join')]/..");

    public LandingPage (String browser) {
        super(browser);
        this.browser = browser;
        waitForVisibility(newMeetingButton);
    }

    public MeetingPage startMeeting () {
        click(newMeetingButton);
        click(instantMeetingOption);

        return new MeetingPage(browser);
    }

    public PreviewPage navigateToPreview (String url) {
        enterText(meetingLinkOrCodeInput, url);
        waitForClickability(joinMeetingButton).click();

        return new PreviewPage(browser);
    }

}
