package com.automation.framework.pages;

import org.openqa.selenium.By;

import com.automation.framework.core.elements.WebElementWrapper;

public class HomePage {
    private WebElementWrapper loginLink = new WebElementWrapper(By.linkText("Login"));

    public HomePage() {
        
    }

    public LoginPage clickLoginLink() {
        loginLink.click();
        return new LoginPage();
    }

    public void waitPageLoad() {
        loginLink.waitElementVisible();
    }
}
