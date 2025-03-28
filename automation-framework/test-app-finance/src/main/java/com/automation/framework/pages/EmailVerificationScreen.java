package com.automation.framework.pages;


import com.automation.framework.core.elements.MobileElementWrapper;
import com.automation.framework.core.mobiledrivers.MobileDriverExtensions;

import io.appium.java_client.AppiumBy;

public class EmailVerificationScreen {

    private MobileElementWrapper emailVerificationLabel = new MobileElementWrapper(AppiumBy.xpath("//*[contains(@text, 'Email Verification')]"));

    private MobileElementWrapper codeField = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Email Verification Code']/..//android.widget.EditText"));

    private MobileElementWrapper submitButton = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Submit']"));

    public EmailVerificationScreen() {
        System.out.println("EmailVerificationScreen");
        emailVerificationLabel.waitElementVisible();
    }

    public boolean isEmailVerificationScreenDisplayed() {
        return emailVerificationLabel.isDisplayed(5);
    }

    public EmailVerificationScreen typeCode(String email) {
        codeField.type(email);
        return this;
    }

    public EmailVerificationScreen clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public DashboardScreen autoFillCodeVerificationForm(String code) {
        typeCode(code);
        return new DashboardScreen();
    }
}
