package com.automation.framework.core.webdrivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.Select;

public class DriverExtensions {

    private static WebDriver getDriver() {
        WebDriver driver = DriverManager.getDriver();
        if (driver == null) {
            throw new IllegalStateException("Appium driver is not initialized.");
        }
        return driver;
    }

    public static void openUrl(String url) {
        WebDriver driver = getDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static WebElement waitElementVisible(By locator, int waitTime) {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean waitElementInvisible(By locator, int waitTime) {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement waitElementClickable(By locator, int waitTime) {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void click(By locator, int waitTime) {
        waitElementClickable(locator, waitTime).click();
    }

    public static void sendKeys(By locator, String text, int waitTime) {
        WebElement element = waitElementVisible(locator, waitTime);
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(By locator, int waitTime) {
        return waitElementVisible(locator, waitTime).getText();
    }

    public static void selectDropdownByText(By locator, String text, int waitTime) {
        WebElement dropdown = waitElementVisible(locator, waitTime);
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
    }

    public static void selectDropdownByWebElement(WebElement dropdownElement, String text) {
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(text);
    }
}
