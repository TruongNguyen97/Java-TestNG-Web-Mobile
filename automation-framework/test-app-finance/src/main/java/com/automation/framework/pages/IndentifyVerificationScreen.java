package com.automation.framework.pages;

import com.automation.framework.core.elements.MobileElementWrapper;

import io.appium.java_client.AppiumBy;

public class IndentifyVerificationScreen {

    private MobileElementWrapper indentifyVerificationLabel = new MobileElementWrapper(AppiumBy.xpath("//*[contains(@text, 'required to complete Intermediate Identity Verification')]"));

    private MobileElementWrapper verifyButton = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Verify Now']"));

    private MobileElementWrapper laterButton = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Later']"));

    public IndentifyVerificationScreen() {
        System.out.println("IndentifyVerificationScreen");
        indentifyVerificationLabel.waitElementVisible();
    }

    public boolean isIndentifyVerificationScreenDisplayed() {
        return indentifyVerificationLabel.isDisplayed(5);
    }

    public void clickVerifyButton() {
        verifyButton.click();
    }

    public void clickLaterButton() {
        laterButton.click();
    }


}
