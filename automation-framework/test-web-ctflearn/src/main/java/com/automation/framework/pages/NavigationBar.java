package com.automation.framework.pages;

import com.automation.framework.core.elements.WebElementWrapper;

import org.openqa.selenium.By;


public class NavigationBar {

    private WebElementWrapper challengeDropdownToggle = new WebElementWrapper(By.cssSelector("a.dropdown-toggle"));
    private WebElementWrapper createChallengeOptionLink = new WebElementWrapper(By.linkText("Create Challenge"));
    private WebElementWrapper myChallengesOptionLink = new WebElementWrapper(By.linkText("My Challenges"));

    private WebElementWrapper avatarImage = new WebElementWrapper(By.cssSelector("#profileDropdown img"));
    private WebElementWrapper logoutLink = new WebElementWrapper(By.cssSelector("a[href*='logout']"));

    public NavigationBar() {

    }

    public CreateChallengePage clickToCreateChallenge() {
        challengeDropdownToggle.click();
        createChallengeOptionLink.click();
        return new CreateChallengePage();
    }

    public MyChallengePage clickToMyChallenges() {
        challengeDropdownToggle.click();
        myChallengesOptionLink.click();
        return new MyChallengePage();
    }

    public HomePage clickToLogout() {
        avatarImage.click();
        logoutLink.click();
        return new HomePage();
    }
}
