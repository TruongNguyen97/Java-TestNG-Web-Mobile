package com.automation.framework.core.mobiledrivers;
import java.util.Optional;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServerManager {

    protected static final String IPADDRESS = Optional.ofNullable(System.getProperty("appium.server.url")).orElse("127.0.0.1");
    protected static final int PORT = Optional.ofNullable(System.getProperty("appium.server.port")).map(Integer::parseInt).orElse(4723);;

    private static AppiumDriverLocalService service;

    public static void startServer() {
        if (service == null) {
            service = new AppiumServiceBuilder()
                .withIPAddress(IPADDRESS)
                .usingPort(PORT)
                .build();

            service.start();
            System.out.println("Appium Server started at: " + service.getUrl());
        }
    }

    public static void stopServer() {
        if (service != null) {
            service.stop();
            System.out.println("Appium Server stopped.");
        }
    }
}