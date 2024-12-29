package com.tdl.util;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class BasePage {
    protected WebDriver driver;
    private final WebDriverWait wait;

    public BasePage () {
        this.driver = BrowserUtils.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-second timeout
    }

    protected WebElement waitForVisibility (By locator) {
        Logger logger = Logger.getLogger(getClass().getName());
        WebElement element;
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Element located by " + locator + " is visible.");
        } catch (TimeoutException e) {
            logger.severe("Timeout while waiting for visibility of element located by: " + locator);
            throw e;
        } catch (Exception e) {
            logger.severe("An error occurred while waiting for visibility of element located by: " + locator + ". Error: " + e.getMessage());
            throw e;
        }

        return element;
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

