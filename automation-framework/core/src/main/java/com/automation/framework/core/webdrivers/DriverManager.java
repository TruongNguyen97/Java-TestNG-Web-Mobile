package com.automation.framework.core.webdrivers;
import org.openqa.selenium.WebDriver;
import com.automation.framework.core.utils.ConfigReader;

public class DriverManager {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void removeDriver() {
        driver.remove();
    }

    public static void initDriver() {
        if (ConfigReader.getProperties() == null) {
            throw new IllegalArgumentException("Not found config data, please run ConfigReader.initConfigReader() first");
        } else {
            String browser = ConfigReader.getProperty("browser.name").toLowerCase();

            switch (browser) {
                case "chrome":
                    setDriver(ChromeCustomDriver.createDriver());
                    break;

                case "edge":
                    setDriver(EdgeCustomDriver.createDriver());
                    break;

                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            removeDriver();
        }
    }
}
