package com.automation.framework.pages;

import com.automation.framework.core.elements.MobileElementWrapper;

import io.appium.java_client.AppiumBy;

public class PortfolioScreen extends UserHomeScreen {

    private MobileElementWrapper PortfolioLabel = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Portfolio']"));

    private MobileElementWrapper currentBalanceValueLabel = new MobileElementWrapper(AppiumBy.xpath("//*[@text='Total Balance'] /following-sibling::android.widget.TextView[1]"));


    public PortfolioScreen() {
        System.out.println("PortfolioScreen");
        PortfolioLabel.waitElementVisible();
    }

    public String getCurrentBalanceValue() {
        return currentBalanceValueLabel.getText();
    }

    public BottomNavigationBar getBottomNavigationBar() {
        return new BottomNavigationBar();
    }
}
