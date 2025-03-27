package com.automation.framework.core.webdrivers;

import com.automation.framework.core.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class EdgeCustomDriver {
    
    public static WebDriver createDriver() {

        String browserPath = ConfigReader.getProperty("webdriver.edgedriver.path");
        String webdriverRemoteUrl = ConfigReader.getProperty("webdriver.remote.url");
        boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("browser.is.headless"));

        EdgeOptions options = new EdgeOptions();
        if (isHeadless) {
            options.addArguments("--headless");
        }

        if (webdriverRemoteUrl == null || webdriverRemoteUrl.isEmpty()) {
            // Create local WebDriver
            if (browserPath == null || browserPath.isEmpty()) { 
                return WebDriverManager.edgedriver().capabilities(options).create();
            } else {
                System.setProperty("webdriver.edge.driver", browserPath);
                return new EdgeDriver(options);
            }
        } else {
            // Remote WebDriver
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(EdgeOptions.CAPABILITY, options);
                return new RemoteWebDriver(new URL(webdriverRemoteUrl), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid remote WebDriver URL: " + webdriverRemoteUrl, e);
            }
        }
    }

}
