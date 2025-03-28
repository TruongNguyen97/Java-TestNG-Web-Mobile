package com.automation.framework.pages;

public class HomePage {

    public HomePage() {
        System.out.println("HomePage");
    }

    public InterstitialWelcomeScreen getWelcomeScreen() {
        return new InterstitialWelcomeScreen();
    }

}
