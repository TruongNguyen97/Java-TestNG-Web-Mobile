package com.automation.framework.pages;

import org.openqa.selenium.By;

import com.automation.framework.core.elements.WebElementWrapper;

public class MyChallengePage {

    public MyChallengePage() {
       
    }

    public DetailChallengePage findAndClickToChallengeTitle(String title) {
        String dynamicLocator = String.format("//span[text()='%s']", title);
        WebElementWrapper expectedChallenge = new WebElementWrapper(By.xpath(dynamicLocator));
        expectedChallenge.click();
        return new DetailChallengePage();
    }

    public NavigationBar getNavigationBar() {
        return new NavigationBar();
    }
}
