package com.tdl.googleMeet.pages;

import com.tdl.util.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By signInButton = By.cssSelector("#m2 [label='Sign into Google Meet ']:first-child");

    public HomePage () {
        super();
        waitForVisibility(signInButton);
    }

    public LoginPage navigateToLoginPage () {
        click(signInButton);

        return new LoginPage();
    }
}
