package com.automation.framework.pages;

public class UserHomeScreen {
    
    public UserHomeScreen() {
        
    }

    

    public NotificationPermissionScreen getNotificationPermissionScreen() {
        return new NotificationPermissionScreen();
    }

    public IndentifyVerificationScreen getIdentifyVerificationScreen() {
        return new IndentifyVerificationScreen();
    }
}
