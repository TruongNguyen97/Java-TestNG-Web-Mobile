package com.automation.framework.pages;


import com.automation.framework.core.elements.MobileElementWrapper;
import com.automation.framework.core.mobiledrivers.MobileDriverExtensions;

import io.appium.java_client.AppiumBy;

public class LoginPasswordScreen {

    private MobileElementWrapper passwordLabel = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Enter your password']"));

    private MobileElementWrapper passwordField = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Password']/..//android.widget.EditText"));

    private MobileElementWrapper nextButton = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Next']"));

    public LoginPasswordScreen() {
        System.out.println("LoginPasswordScreen");
        passwordLabel.waitElementVisible();
    }

    public boolean isLoginPasswordScreenDisplayed() {
        return passwordLabel.isDisplayed(5);
    }

    public LoginPasswordScreen typePassword(String email) {
        passwordField.type(email);
        return this;
    }

    public LoginPasswordScreen clickNextButton() {
        nextButton.click();
        return this;
    }

    public EmailVerificationScreen autoFillLoginPasswordForm(String password) {
        typePassword(password);
        clickNextButton();
        MobileDriverExtensions.waitForHandlingManualCaptcha();
        return new EmailVerificationScreen();
    }
}
