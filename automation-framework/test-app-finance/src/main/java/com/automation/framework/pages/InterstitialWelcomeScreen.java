package com.automation.framework.pages;


import com.automation.framework.core.elements.MobileElementWrapper;


import io.appium.java_client.AppiumBy;

public class InterstitialWelcomeScreen {

     // // 1. Start - Click to Login Link
    // @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"_Az\"]")
    // private WebElement LoginLink;

    // // 2. Login Email Form - Fill Email Account - Click Next Button
    // @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='Email/Phone (without country code)']")
    // private WebElement EmailField;

    // @AndroidFindBy(xpath = "//android.widget.Button[@text='Next']")
    // private WebElement LoginNextButton;


    private MobileElementWrapper welcomeLabel = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Welcome to Binance']"));

    private MobileElementWrapper loginLink = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Log in']"));

    public InterstitialWelcomeScreen() {
        System.out.println("InterstitialWelcomeScreen");
        welcomeLabel.setWaitTime(60).waitElementVisible();
    }

    public boolean isWelcomeScreenDisplayed() {
        return welcomeLabel.isDisplayed(5);
    }

    public LoginEmailScreen clickLoginLink() {
        loginLink.click();
        return new LoginEmailScreen();
    }


}
