package com.automation.framework.pages;

import com.automation.framework.core.elements.WebElementWrapper;
import com.automation.framework.models.Challenge;

import org.openqa.selenium.By;


public class CreateChallengePage {
    private WebElementWrapper titleField = new WebElementWrapper(By.id("title"));
    private WebElementWrapper flagField = new WebElementWrapper(By.id("flag"));
    private WebElementWrapper descriptionField = new WebElementWrapper(By.id("flask-pagedown-description"));
    private WebElementWrapper categoryDropdown = new WebElementWrapper(By.id("category"));
    private WebElementWrapper howToSolveField = new WebElementWrapper(By.id("howtosolve"));
    private WebElementWrapper pointDropdown = new WebElementWrapper(By.id("points"));
    private WebElementWrapper submitButton = new WebElementWrapper(By.cssSelector(".btn-success"));

    public CreateChallengePage() {

    }

    public CreateChallengePage createChallenge(Challenge challenge) {
        if (!challenge.getTitle().isEmpty()) {
            titleField.type(challenge.getTitle());
        }
        if (!challenge.getFlag().isEmpty()) {
            flagField.type(challenge.getFlag());
        }
        if (!challenge.getDescription().isEmpty()) {
            descriptionField.type(challenge.getDescription());
        }
        if (!challenge.getCategory().isEmpty()) {
            categoryDropdown.selectDropdownByText(challenge.getCategory());
        }
        if (!challenge.getHowToSolve().isEmpty()) {
            howToSolveField.type(challenge.getHowToSolve());
        }
        if (!challenge.getPoints().isEmpty()) {
            pointDropdown.selectDropdownByText(challenge.getPoints());
        }
        
        submitButton.click();
        return this;
    }

    public NavigationBar getNavigationBar() {
        return new NavigationBar();
    }
}
