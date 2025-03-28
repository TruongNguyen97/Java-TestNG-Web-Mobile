package com.automation.framework.core.mobiledrivers;

import java.net.MalformedURLException;
import java.net.URL;

import com.automation.framework.core.utils.ConfigReader;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class IOSCustomDriver {
    public static IOSDriver createDriver() {

        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setDeviceName(ConfigReader.getProperty("ios.deviceName"))
                .setBundleId(ConfigReader.getProperty("ios.bundleId"))
                .setAutomationName("XCUITest");

        try {
            return new IOSDriver(new URL(ConfigReader.getProperty("appium.server.url")), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }
}
