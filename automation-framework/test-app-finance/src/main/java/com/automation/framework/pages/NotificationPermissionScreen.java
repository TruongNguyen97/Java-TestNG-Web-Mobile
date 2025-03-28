package com.automation.framework.pages;

import com.automation.framework.core.elements.MobileElementWrapper;

import io.appium.java_client.AppiumBy;

public class NotificationPermissionScreen {

    private MobileElementWrapper notificationPermissionLabel = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Allow Binance to send you notifications?']"));

    private MobileElementWrapper noAllowButton = new MobileElementWrapper(AppiumBy.xpath("//*[contains(@text, 't allow')]"));

    private MobileElementWrapper allowButton = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Allow']"));

    public NotificationPermissionScreen() {
        System.out.println("NotificationPermissionScreen");
        notificationPermissionLabel.waitElementVisible();
    }

    public boolean isNotificationPermissionScreenDisplayed() {
        return notificationPermissionLabel.isDisplayed(5);
    }

    public void clickAllowButton() {
        allowButton.click();
    }

    public void clickNoAllowButton() {
        noAllowButton.click();
    }


}
