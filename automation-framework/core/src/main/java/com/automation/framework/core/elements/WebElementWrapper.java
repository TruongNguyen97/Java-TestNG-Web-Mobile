package com.automation.framework.core.elements;

import com.automation.framework.core.webdrivers.DriverExtensions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebElementWrapper {
    private By locator;
    private int waitTime;

    public WebElementWrapper(By locator, int waitTime) {
        this.locator = locator;
        this.waitTime = waitTime;
    }

    public WebElementWrapper(By locator) {
        this.locator = locator;
        this.waitTime = 30;
    }

    public WebElementWrapper setWaitTime(int waitTime) {
        this.waitTime = waitTime;
        return this;
    }

    public WebElementWrapper click() {
        DriverExtensions.click(locator, waitTime);
        return this;
    }

    public WebElementWrapper type(String text) {
        DriverExtensions.sendKeys(locator, text, waitTime);
        return this;
    }

    public String getText() {
        return DriverExtensions.getText(locator, waitTime);
    }

    public WebElement waitElementVisible() {
        
        return DriverExtensions.waitElementVisible(locator, waitTime);
    }

    public boolean waitElementInvisible() {
        return DriverExtensions.waitElementInvisible(locator, waitTime);
    }

    public WebElement waitElementClickable() {
        return DriverExtensions.waitElementClickable(locator, waitTime);
    }

    public WebElementWrapper selectDropdownByText(String text) {
        WebElement dropdown = waitElementVisible();
        DriverExtensions.selectDropdownByWebElement(dropdown, text);
        return this;
    }
}
