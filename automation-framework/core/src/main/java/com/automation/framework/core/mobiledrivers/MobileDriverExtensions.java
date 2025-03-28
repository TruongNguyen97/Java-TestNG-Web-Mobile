package com.automation.framework.core.mobiledrivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.framework.core.utils.ConfigReader;

import java.time.Duration;
import java.util.Optional;

public class MobileDriverExtensions {

    private static AppiumDriver getDriver() {
        AppiumDriver driver = MobileDriverManager.getDriver();
        if (driver == null) {
            throw new IllegalStateException("Appium driver is not initialized.");
        }

        return driver;
    }

    public static WebElement waitElementVisible(By locator, int waitTime) {
        AppiumDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean waitElementInvisible(By locator, int waitTime) {
        AppiumDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement waitElementClickable(By locator, int waitTime) {
        AppiumDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
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

    public static void waitForHandlingManualCaptcha(){
        long time = Optional.ofNullable(ConfigReader.getProperty("captcha.wait.time"))
            .map(Integer::parseInt)  
            .orElse(1000);
        proactiveSleep(time);
    }

    public static void proactiveSleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void launchApp() {
        String platform = ConfigReader.getProperty("mobile.platform").toLowerCase();

        switch (platform) {
            case "android":
                ((AndroidDriver) getDriver()).startActivity(
                    new Activity(
                        ConfigReader.getProperty("android.appPackage"), 
                        ConfigReader.getProperty("android.appActivity")));

                break;
            default:
                throw new RuntimeException("Unsupported platform: " + platform);
        }
    }

    
}
