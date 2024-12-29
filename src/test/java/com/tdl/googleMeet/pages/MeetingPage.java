package com.tdl.googleMeet.pages;

import com.tdl.util.BasePage;
import org.openqa.selenium.By;

public class MeetingPage extends BasePage {

    private final By meetingLinkText = By.xpath("//div[contains(text(), 'meet.google.com')]");
    private final By turnOnMicButton = By.cssSelector("[aria-label='Turn on microphone']");
    private final By turnOffMicButton = By.cssSelector("[aria-label='Turn off microphone']");
    private final By sendReactionButton = By.cssSelector("[aria-label='Send a reaction']");
    private final By reactionsToolbar = By.cssSelector("[role='toolbar']");
    private final By leaveCallButton = By.cssSelector("[aria-label='Leave call']");


    public MeetingPage () {
        super();
        waitForVisibility(meetingLinkText);
    }

    public String getMeetingLink () {
        return getText(meetingLinkText);
    }

    public MeetingPage verifyButtons () {
        click(turnOffMicButton);
        click(turnOnMicButton);
        click(sendReactionButton);
        waitForVisibility(reactionsToolbar);

        return this;
    }

    public MeetingPage verifyLeaveCallButton () {
        String cssProp = getCssProperty(leaveCallButton, "background-color");

        return this;
    }


}
