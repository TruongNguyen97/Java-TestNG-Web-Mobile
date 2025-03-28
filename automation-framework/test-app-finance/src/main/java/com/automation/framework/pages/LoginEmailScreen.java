package com.automation.framework.pages;


import com.automation.framework.core.elements.MobileElementWrapper;
import com.automation.framework.core.mobiledrivers.MobileDriverExtensions;

import io.appium.java_client.AppiumBy;

public class LoginEmailScreen {

    private MobileElementWrapper loginLabel = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Log in']"));

    private MobileElementWrapper emailLabel = new MobileElementWrapper(AppiumBy.xpath("//*[contains(@text, 'Phone number')]"));

    private MobileElementWrapper emailField = new MobileElementWrapper(AppiumBy.xpath("//*[contains(@text, 'Phone number')]/..//android.widget.EditText"));

    private MobileElementWrapper nextButton = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Next']"));

    public LoginEmailScreen() {
        System.out.println("LoginEmailScreen");
        loginLabel.waitElementVisible();
        emailLabel.waitElementVisible();
    }

    public boolean isLoginEmailScreenDisplayed() {
        return loginLabel.isDisplayed(5);
    }

    public LoginEmailScreen typeEmail(String email) {
        emailField.type(email);
        return this;
    }

    public LoginEmailScreen clickNextButton() {
        nextButton.click();
        return this;
    }

    public LoginPasswordScreen autoFillLoginEmailForm(String email) {
        typeEmail(email);
        clickNextButton();
        MobileDriverExtensions.waitForHandlingManualCaptcha();

        return new LoginPasswordScreen();
    }
}
