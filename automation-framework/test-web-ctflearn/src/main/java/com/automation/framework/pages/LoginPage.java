package com.automation.framework.pages;
import com.automation.framework.core.elements.WebElementWrapper;
import com.automation.framework.models.Account;

import org.openqa.selenium.By;

public class LoginPage {

    private WebElementWrapper usernameField = new WebElementWrapper(By.id("identifier"));
    private WebElementWrapper passwordField = new WebElementWrapper(By.id("password"));
    private WebElementWrapper loginButton = new WebElementWrapper(By.cssSelector(".mt-2"));

    public LoginPage() {
        
    }

    public DashboardPage loginWithValidAccount(Account account) {
        login(account.getUsername(), account.getPassword());
        return new DashboardPage();
    }

    public void login(String username, String password) {
        if (!username.isEmpty()) {
            usernameField.type(username);
        }
        if (!password.isEmpty()) {
            passwordField.type(password);
        }
        loginButton.click();
    }
}
