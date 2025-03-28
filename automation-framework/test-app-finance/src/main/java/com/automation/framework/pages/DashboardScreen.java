package com.automation.framework.pages;



public class DashboardScreen extends UserHomeScreen {

    public DashboardScreen() {
        super();
        System.out.println("DashboardScreen");
    }

    public BottomNavigationBar getBottomNavigationBar() {
        return new BottomNavigationBar();
    }
}
