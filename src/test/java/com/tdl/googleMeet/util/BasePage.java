package com.tdl.googleMeet.util;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

/**
 * BasePage class provides reusable methods for interacting with web elements
 * and handling browser automation using Selenium WebDriver.
 */
public class BasePage {
    protected WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Initializes the BasePage with a specific browser instance.
     *
     * @param browser The name of the browser (e.g., "chrome", "firefox") to use.
     */
    public BasePage(String browser) {
        this.driver = BrowserUtils.getDriver(browser);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10-second timeout
    }

    /**
     * Waits for an element located by the given locator to become visible.
     *
     * @param locator The Selenium By locator used to find the element.
     * @return The visible WebElement.
     */
    protected WebElement waitForVisibility(By locator) {
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

    /**
     * Waits for an element to become visible and then performs a click action.
     *
     * @param locator The Selenium By locator used to find the element.
     */
    protected void click(By locator) {
        waitForVisibility(locator).click();
    }

    /**
     * Waits for an input element to become visible, clears its existing value, and enters new text.
     *
     * @param locator The Selenium By locator used to find the input element.
     * @param text    The text to enter into the input element.
     */
    protected void enterText(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Retrieves the visible text of an element located by the given locator.
     *
     * @param locator The Selenium By locator used to find the element.
     * @return A String containing the visible text of the element.
     */
    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    /**
     * Retrieves the value of a specified CSS property from an element.
     *
     * @param locator The Selenium By locator used to find the element.
     * @param prop    The name of the CSS property to retrieve.
     * @return A String containing the value of the specified CSS property.
     */
    protected String getCssProperty(By locator, String prop) {
        return waitForVisibility(locator).getCssValue(prop);
    }
}
