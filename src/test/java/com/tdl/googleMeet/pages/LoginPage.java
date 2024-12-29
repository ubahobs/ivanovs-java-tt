package com.tdl.googleMeet.pages;

import com.tdl.googleMeet.util.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final String browser;

    private final By emailInput = By.cssSelector("[type='email']");
    private final By passwordInput = By.cssSelector("[type='password']");
    private final By nextButton = By.xpath("//span[contains(text(), 'Next')]");

    public LoginPage (String browser) {
        super(browser);
        this.browser = browser;
        waitForVisibility(emailInput);
    }

    public LandingPage login (String email, String password) {
        enterText(emailInput, email);
        click(nextButton);
        enterText(passwordInput, password);
        click(nextButton);

        return new LandingPage(browser);
    }
}
