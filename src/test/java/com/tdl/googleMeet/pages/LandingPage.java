package com.tdl.googleMeet.pages;

import com.tdl.util.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends BasePage {

//    permission[type="microphone camera"]

    private By newMeetingButton = By.xpath("//span[contains(text(), 'New meeting')]");
    private By instantMeetingOption = By.cssSelector("[aria-label='Start an instant meeting']");
    private By meetingLinkOrCodeInput = By.cssSelector("[placeholder='Enter a code or link']");
    private By joinMeetingButton = By.xpath("//span[contains(text(), 'Join')]");

    public LandingPage() {
        super();
        waitForVisibility(newMeetingButton);
    }

    public MeetingPage startMeeting () {
        click(newMeetingButton);
        click(instantMeetingOption);

        return new MeetingPage();
    }

    public MeetingPage joinMeeting (String url) {
        enterText(meetingLinkOrCodeInput, url);
        click(joinMeetingButton);

        return new MeetingPage();
    }




}
