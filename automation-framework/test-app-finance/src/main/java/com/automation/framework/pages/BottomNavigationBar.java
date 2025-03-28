package com.automation.framework.pages;

import com.automation.framework.core.elements.MobileElementWrapper;

import io.appium.java_client.AppiumBy;

public class BottomNavigationBar {
    private MobileElementWrapper portfolioLabel = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Portfolio']"));

    public BottomNavigationBar() {
        System.out.println("BottomNavigationBar");
    }

    public PortfolioScreen clickPortfolioLabel() {
        portfolioLabel.click();
        return new PortfolioScreen();
    }
}
