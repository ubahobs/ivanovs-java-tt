package com.tdl.googleMeet.util;

import com.tdl.util.AllureUtil;
import com.tdl.util.ConfigUtils;
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
    private final String waitTime = ConfigUtils.getConfigProperty("default.wait");
    private final Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Initializes the BasePage with a specific browser instance.
     *
     * @param browser The name of the browser (e.g., "chrome", "firefox") to use.
     */
    public BasePage(String browser) {
        this.driver = BrowserUtils.getDriver(browser);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(waitTime))); // 10-second timeout
    }

    /**
     * Checks if an element located by the given locator is visible on the page.
     *
     * @param locator The Selenium By locator used to find the element.
     * @return true if the element is visible, false otherwise.
     */
    public boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            AllureUtil.logStepIntoReport(String.format("Element located by %s is visible.", locator));
            logger.info(String.format("Element located by %s is visible.", locator));
            return true;
        } catch (Exception e) {
            AllureUtil.logStepIntoReport(String.format("Element located by %s is not visible. Error: %s", locator, e.getMessage()));
            logger.warning(String.format("Element located by %s is not visible. Error: %s", locator, e.getMessage()));
            return false;
        }
    }

    /**
     * Waits for an element located by the given locator to become visible.
     *
     * @param locator The Selenium By locator used to find the element.
     * @return The visible WebElement.
     */
    protected WebElement waitForVisibility(By locator) {
        WebElement element;
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            AllureUtil.logStepIntoReport(String.format("Element located by %s is visible.", locator));
            logger.info(String.format("Element located by %s is visible.", locator));
        } catch (TimeoutException e) {
            AllureUtil.logStepIntoReport(String.format("Element located by %s is not found within %s seconds.", locator, waitTime));
            logger.severe(String.format("Timeout while waiting for visibility of element located by %s", locator));
            throw e;
        } catch (Exception e) {
            AllureUtil.logStepIntoReport(String.format("An error occurred while waiting for visibility of element located by %s. Error: %s", locator, e.getMessage()));
            logger.severe(String.format("An error occurred while waiting for visibility of element located by %s. Error: %s", locator, e.getMessage()));
            throw e;
        }
        return element;
    }

    /**
     * Waits for an element located by the given locator to become clickable.
     *
     * @param locator The Selenium By locator used to find the element.
     * @return The clickable WebElement.
     */
    protected WebElement waitForClickability(By locator) {
        WebElement element;
        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            AllureUtil.logStepIntoReport(String.format("Element located by %s is clickable.", locator));
            logger.info(String.format("Element located by %s is clickable.", locator));
        } catch (TimeoutException e) {
            AllureUtil.logStepIntoReport(String.format("Element located by %s is not clickable within %s seconds.", locator, waitTime));
            logger.severe(String.format("Timeout while waiting for clickability of element located by %s", locator));
            throw e;
        } catch (Exception e) {
            AllureUtil.logStepIntoReport(String.format("An error occurred while waiting for clickability of element located by %s. Error: %s", locator, e.getMessage()));
            logger.severe(String.format("An error occurred while waiting for clickability of element located by %s. Error: %s", locator, e.getMessage()));
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
        try {
            WebElement element = waitForVisibility(locator);
            element.click();
            AllureUtil.logStepIntoReport(String.format("Clicked on element located by %s.", locator));
            logger.info(String.format("Clicked on element located by %s.", locator));
        } catch (Exception e) {
            AllureUtil.logStepIntoReport(String.format("Failed to click on element located by %s. Error: %s", locator, e.getMessage()));
            logger.severe(String.format("Failed to click on element located by %s. Error: %s", locator, e.getMessage()));
            throw e;
        }
    }

    /**
     * Waits for an input element to become visible, clears its existing value, and enters new text.
     *
     * @param locator The Selenium By locator used to find the input element.
     * @param text    The text to enter into the input element.
     */
    protected void enterText(By locator, String text) {
        try {
            WebElement element = waitForVisibility(locator);
            element.clear();
            element.sendKeys(text);
            AllureUtil.logStepIntoReport(String.format("Entered text '%s' into element located by %s.", text, locator));
            logger.info(String.format("Entered text '%s' into element located by %s.", text, locator));
        } catch (Exception e) {
            AllureUtil.logStepIntoReport(String.format("Failed to enter text '%s' into element located by %s. Error: %s", text, locator, e.getMessage()));
            logger.severe(String.format("Failed to enter text '%s' into element located by %s. Error: %s", text, locator, e.getMessage()));
            throw e;
        }
    }

    /**
     * Retrieves the visible text of an element located by the given locator.
     *
     * @param locator The Selenium By locator used to find the element.
     * @return A String containing the visible text of the element.
     */
    protected String getText(By locator) {
        try {
            String text = waitForVisibility(locator).getText();
            AllureUtil.logStepIntoReport(String.format("Retrieved text '%s' from element located by %s.", text, locator));
            logger.info(String.format("Retrieved text '%s' from element located by %s.", text, locator));
            return text;
        } catch (Exception e) {
            AllureUtil.logStepIntoReport(String.format("Failed to retrieve text from element located by %s. Error: %s", locator, e.getMessage()));
            logger.severe(String.format("Failed to retrieve text from element located by %s. Error: %s", locator, e.getMessage()));
            throw e;
        }
    }

    /**
     * Retrieves the value of a specified CSS property from an element.
     *
     * @param locator The Selenium By locator used to find the element.
     * @param prop    The name of the CSS property to retrieve.
     * @return A String containing the value of the specified CSS property.
     */
    protected String getCssProperty(By locator, String prop) {
        try {
            String value = waitForVisibility(locator).getCssValue(prop);
            AllureUtil.logStepIntoReport(String.format("Retrieved CSS property '%s' with value '%s' from element located by %s.", prop, value, locator));
            logger.info(String.format("Retrieved CSS property '%s' with value '%s' from element located by %s.", prop, value, locator));
            return value;
        } catch (Exception e) {
            AllureUtil.logStepIntoReport(String.format("Failed to retrieve CSS property '%s' from element located by %s. Error: %s", prop, locator, e.getMessage()));
            logger.severe(String.format("Failed to retrieve CSS property '%s' from element located by %s. Error: %s", prop, locator, e.getMessage()));
            throw e;
        }
    }
}
