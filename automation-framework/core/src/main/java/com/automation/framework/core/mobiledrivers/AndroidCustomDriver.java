package com.automation.framework.core.mobiledrivers;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import com.automation.framework.core.utils.ConfigReader;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.appmanagement.ApplicationState;

public class AndroidCustomDriver {
    public static AndroidDriver createDriver() {

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName(ConfigReader.getProperty("android.deviceName"))
                .setAppPackage(ConfigReader.getProperty("android.appPackage"))
                .setAppActivity(ConfigReader.getProperty("android.appActivity"))
                .setAutomationName("UiAutomator2")
                .setNoReset(false) // true -> Preserve app state (do not reset data)
                .setFullReset(false) // false -> Do not uninstall and reinstall the app
                .setNewCommandTimeout(Duration.ofSeconds(300))
                .eventTimings();

        try {
            return new AndroidDriver(new URL(ConfigReader.getProperty("appium.server.url")), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }

   
    
}
