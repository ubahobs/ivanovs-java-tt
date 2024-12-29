package com.tdl.googleMeet.tests;

import com.tdl.googleMeet.Application;
import com.tdl.googleMeet.util.AudioVideo;
import com.tdl.util.ConfigUtils;
import com.tdl.googleMeet.util.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tdl.googleMeet.util.BrowserUtils;

public class GoogleMeetTest extends TestBase {

    @Test(description = "Google Meet test")
    public void googleMeetTest() {
        String email1 = ConfigUtils.getConfigProperty("meet.user1.email");
        String password1 = ConfigUtils.getConfigProperty("meet.user1.password");

        String email2 = ConfigUtils.getConfigProperty("meet.user2.email");
        String password2 = ConfigUtils.getConfigProperty("meet.user2.password");

        String meetingLink = Application
                .navigateToHomePage("chrome")
                .navigateToLoginPage()
                .login(email1, password1)
                .startMeeting()
                .addUserToMeeting(email2)
                .verifyButtons()
                .verifyLeaveCallButton()
                .getMeetingLink();

        Application
                .navigateToHomePage("firefox")
                .navigateToLoginPage()
                .login(email2, password2)
                .navigateToPreview(meetingLink)
                .joinMeeting()
                .verifyButtons()
                .verifyLeaveCallButton();

        AudioVideo user1Media = new AudioVideo(BrowserUtils.getDriver("chrome"));
        user1Media.isAudioPlaying();
        user1Media.isVideoPlaying();

        AudioVideo user2Media = new AudioVideo(BrowserUtils.getDriver("firefox"));
        user2Media.isAudioPlaying();
        user2Media.isVideoPlaying();

    }
}
