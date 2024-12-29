package com.tdl.googleMeet.pages;

import com.tdl.util.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private By emailInput = By.cssSelector("[type='email']");
    private By passwordInput = By.cssSelector("[type='password']");
    private By nextButton = By.xpath("//span[contains(text(), 'Next')]");

    public LoginPage () {
        super();
        waitForVisibility(emailInput);
    }

    public LandingPage login (String email, String password) {
        enterText(emailInput, email);
        click(nextButton);
        enterText(passwordInput, password);
        click(nextButton);

        return new LandingPage();
    }
}
