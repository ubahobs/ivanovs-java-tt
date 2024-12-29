package com.tdl.googleMeet.pages;

import com.tdl.googleMeet.util.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final String browser;

    private final By signInButton = By.cssSelector("#m2 [label='Sign into Google Meet ']:first-child");

    public HomePage (String browser) {
        super(browser);
        this.browser = browser;
        waitForVisibility(signInButton);
    }

    @Step("Naviage to the login page")
    public LoginPage navigateToLoginPage () {
        click(signInButton);

        return new LoginPage(browser);
    }
}
