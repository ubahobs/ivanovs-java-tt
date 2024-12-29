package com.tdl.googleMeet.pages;

import com.tdl.googleMeet.util.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Objects;

public class MeetingPage extends BasePage {

    private final String browser;

    private final By addOthersButton = By.xpath("//span[contains(text(), 'Add others')]");
    private final By emailInput = By.cssSelector("[placeholder='Enter name or email']");
    private final By searchResultFirstOption = By.cssSelector("[aria-label='Search results'] li:first-child");
    private final By sendEmailButton = By.xpath("//span[contains(text(), 'Send email')]");
    private final By gotItPopUpButton = By.xpath("//span[contains(text(), 'Got it')]");

    private final By meetingLinkText = By.xpath("//div[contains(text(), 'meet.google.com')]");
    private final By turnOnMicButton = By.cssSelector("[aria-label='Turn on microphone']");
    private final By turnOffMicButton = By.cssSelector("[aria-label='Turn off microphone']");
    private final By sendReactionButton = By.cssSelector("[aria-label='Send a reaction']");
    private final By reactionsToolbar = By.cssSelector("[role='toolbar']");
    private final By leaveCallButton = By.cssSelector("[aria-label='Leave call']");


    public MeetingPage (String browser) {
        super(browser);
        this.browser = browser;
        waitForVisibility(turnOffMicButton);
        if (isVisible(gotItPopUpButton)) click(gotItPopUpButton);
    }

    public MeetingPage addUserToMeeting (String email) {
        click(addOthersButton);
        enterText(emailInput, email);
        String result = waitForVisibility(searchResultFirstOption).getAttribute("data-id");
        Assert.assertEquals(email, result,
                "The email displayed in the search result should be equal to: " + email + " actual: " + result);
        click(searchResultFirstOption);
        click(sendEmailButton);

        return this;
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
        String expectedColor;
        if (Objects.equals(browser, "chrome"))
            expectedColor = "rgba(220, 54, 46, 1)";
        else
            expectedColor = "rgb(220, 54, 46)";

        String actualColor = getCssProperty(leaveCallButton, "background-color");
        Assert.assertEquals(actualColor, expectedColor,
                "The color of the leave call button should be equal to: " + expectedColor + " actual: " + actualColor);
        return this;
    }


}
