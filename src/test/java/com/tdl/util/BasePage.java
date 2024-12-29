package com.tdl.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private final WebDriverWait wait;

    public BasePage () {
        this.driver = BrowserUtils.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-second timeout
    }

    protected WebElement waitForVisibility (By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click (By locator) {
        waitForVisibility(locator).click();
    }

    protected void enterText (By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText (By locator) {
        return waitForVisibility(locator).getText();
    }

    protected String getCssProperty (By locator, String prop) {
        return waitForVisibility(locator).getCssValue(prop);
    }

}

