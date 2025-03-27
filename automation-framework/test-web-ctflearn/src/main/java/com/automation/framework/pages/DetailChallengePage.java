package com.automation.framework.pages;

import org.openqa.selenium.By;

import com.automation.framework.core.elements.WebElementWrapper;
import com.automation.framework.models.Challenge;

public class DetailChallengePage {

    private WebElementWrapper titleLabel = new WebElementWrapper(By.id("title-display"));
    private WebElementWrapper descriptionLabel = new WebElementWrapper(By.id("description-display"));
    private WebElementWrapper pointLabel = new WebElementWrapper(By.id("points-display"));
    private WebElementWrapper categoryLabel = new WebElementWrapper(By.id("category-display"));
    

    public DetailChallengePage() {
        
    }

    public Challenge getChallengeDetail() {
        Challenge challenge = new Challenge();
        challenge.setTitle(titleLabel.getText());
        challenge.setDescription(descriptionLabel.getText());
        challenge.setPoints(pointLabel.getText());
        challenge.setCategory(categoryLabel.getText());
        return challenge;
    }

    public NavigationBar getNavigationBar() {
        return new NavigationBar();
    }
}
