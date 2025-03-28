package com.automation.framework.core.mobiledrivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;

import com.automation.framework.core.utils.ConfigReader;

public class MobileDriverManager {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AppiumDriver appiumDriver) {
        driver.set(appiumDriver);
    }

    public static void removeDriver() {
        driver.remove();
    }

    public static void initDriver() {
        if (ConfigReader.getProperties() == null) {
            throw new IllegalArgumentException("Not found config data, please run ConfigReader.initConfigReader() first");
        }

        String platform = ConfigReader.getProperty("mobile.platform").toLowerCase();
        AppiumDriver appiumDriver;

        switch (platform) {
            case "android":
                appiumDriver = AndroidCustomDriver.createDriver();
                break;
            case "ios":
                appiumDriver = IOSCustomDriver.createDriver();
                throw new RuntimeException("Currently, the IOS platform is not supported");
            default:
                throw new RuntimeException("Unsupported platform: " + platform);
        }

        setDriver(appiumDriver);

        waitForAppReady(180, 
                    5, 5);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            removeDriver();
        }
    }

    public static void waitForAppReady(int maxWaitTimeInSeconds, int requiredStableChecks, int checkIntervalMillis){
        waitForAppState(maxWaitTimeInSeconds, requiredStableChecks, 
            checkIntervalMillis, ApplicationState.RUNNING_IN_FOREGROUND);
    }

    public static void waitForAppRunInBackground(int maxWaitTimeInSeconds, int requiredStableChecks, int checkIntervalMillis){
        waitForAppState(maxWaitTimeInSeconds, requiredStableChecks, 
            checkIntervalMillis, ApplicationState.RUNNING_IN_BACKGROUND);
    }

    public static void waitForAppState(int maxWaitTimeInSeconds, int requiredStableChecks, 
        int checkIntervalMillis, ApplicationState expectedState) {
        int elapsedTime = 0;
        int stableCount = 0;
        String appPackage = ConfigReader.getProperty("android.appPackage");

        if (checkIntervalMillis > maxWaitTimeInSeconds * 1000) {
            throw new IllegalArgumentException("Error: checkIntervalMillis (" 
                + checkIntervalMillis + "ms) cannot be greater than maxWaitTimeInSeconds (" + maxWaitTimeInSeconds + "s)");
        }

        if (!ConfigReader.getProperty("mobile.platform").toLowerCase().equals("android")) {
            throw new RuntimeException("Currently, this method is only for Android platform");
        }

        while (elapsedTime < maxWaitTimeInSeconds * 1000) {
            ApplicationState state = ((AndroidDriver)getDriver()).queryAppState(appPackage);

            if (state == expectedState) {
                stableCount++;
                System.out.println("App is ready check state " + expectedState + " -> "+ stableCount + "/" + requiredStableChecks);

                if (stableCount >= requiredStableChecks) {
                    System.out.println("App is fully check!");
                    return;
                }
            } else {
                stableCount = 0; // Reset stable count if app is not show expected state
                System.out.println("App is not correctly state, resetting check count..., current state: " + state);
            }

            try {
                Thread.sleep(checkIntervalMillis * 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            elapsedTime += checkIntervalMillis * 1000;
        }

        throw new RuntimeException("App is not stable as expected as " + expectedState 
            + " after " + maxWaitTimeInSeconds + " seconds!");
    }
}