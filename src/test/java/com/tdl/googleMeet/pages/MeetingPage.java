package com.tdl.googleMeet.pages;

import com.tdl.util.BasePage;
import org.openqa.selenium.By;

public class MeetingPage extends BasePage {

    private By meetingLinkText = By.xpath("//div[contains(text(), 'meet.google.com')]");
    private By turnOnMicButton = By.cssSelector("[aria-label='Turn on microphone']");
    private By turnOffMicButton = By.cssSelector("[aria-label='Turn off microphone']");
    private By sendReactionButton = By.cssSelector("[aria-label='Send a reaction']");
    private By reactionsToolbar = By.cssSelector("[role='toolbar']");
    private By leaveCallButton = By.cssSelector("aria-label='Leave call'");


    public MeetingPage () {
        super();
        waitForVisibility(meetingLinkText);
    }

    public String getMeetingLink () {
        return getText(meetingLinkText);
    }

    public MeetingPage verifyButtons () {
        click(turnOnMicButton);
        click(turnOffMicButton);
        click(sendReactionButton);
        waitForVisibility(reactionsToolbar);

        return this;
    }

    public MeetingPage verifyLeaveCallButton () {
        getCssProperty(leaveCallButton, "background-color")
    }


}
