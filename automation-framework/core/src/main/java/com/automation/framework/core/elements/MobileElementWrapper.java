package com.automation.framework.core.elements;

import com.automation.framework.core.mobiledrivers.MobileDriverExtensions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class MobileElementWrapper {
    private By locator;
    private int waitTime;

    public MobileElementWrapper(By locator, int waitTime) {
        this.locator = locator;
        this.waitTime = waitTime;
    }

    public MobileElementWrapper(By locator) {
        this.locator = locator;
        this.waitTime = Optional.ofNullable(System.getProperty("mobiledriver.wait.time"))
            .map(Integer::parseInt)  
            .orElse(30);
    }

    public MobileElementWrapper setWaitTime(int waitTime) {
        this.waitTime = waitTime;
        return this;
    }

    public MobileElementWrapper click() {
        MobileDriverExtensions.click(locator, waitTime);
        return this;
    }

    public MobileElementWrapper type(String text) {
        MobileDriverExtensions.sendKeys(locator, text, waitTime);
        return this;
    }

    public String getText() {
        return MobileDriverExtensions.getText(locator, waitTime);
    }

    public WebElement waitElementVisible() {
        return MobileDriverExtensions.waitElementVisible(locator, waitTime);
    }

    public boolean waitElementInvisible() {
        return MobileDriverExtensions.waitElementInvisible(locator, waitTime);
    }

    public WebElement waitElementClickable() {
        return MobileDriverExtensions.waitElementClickable(locator, waitTime);
    }

    public boolean isDisplayed(int limitWaitTime) {
        if (MobileDriverExtensions.waitElementInvisible(locator, limitWaitTime)){
            return false;
        }
            
        return true;
    }

}
