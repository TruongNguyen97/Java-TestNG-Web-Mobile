package com.automation.framework;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;

import com.automation.framework.core.mobiledrivers.MobileDriverExtensions;
import com.automation.framework.core.mobiledrivers.MobileDriverManager;
import com.automation.framework.models.Account;
import com.automation.framework.pages.DashboardScreen;
import com.automation.framework.pages.EmailVerificationScreen;
import com.automation.framework.pages.PortfolioScreen;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class MyInvestmentTest extends BaseTest {

    public MyInvestmentTest() throws JsonParseException, JsonMappingException, IOException {
        super();
    }

    @DataProvider(name = "newCreatedUser")
    public Object[] userDataProvider() {
        return new Object[]{
            accountDataMap.get("account_new_created_user")
        };
    }

    @Test(dataProvider = "newCreatedUser")
    public void testViewCurrentBalanceOfNewCreatedUserSuccessfully(Account account) {
        mailHelper.deleteAllEmailOfAccount(account);

        EmailVerificationScreen emailVerificationScreen = homePage
            .getWelcomeScreen()
            .clickLoginLink()
            .autoFillLoginEmailForm(account.getEmail())
            .autoFillLoginPasswordForm(account.getPassword());

        String verificationCode = mailHelper.getLoginVerificationCode(account);
        
        DashboardScreen dashboardScreen = emailVerificationScreen
            .autoFillCodeVerificationForm(verificationCode);

        // After user fill verification code, the app will automatically exit.
        // I'm not sure, it can be a issue or bug
        // So, we need to hanle for re-open the app and navigate to the Portfolio screen
        MobileDriverManager.waitForAppRunInBackground(60, 5, 5);
        MobileDriverExtensions.launchApp();
        MobileDriverManager.waitForAppReady(120, 5, 5);
        
        dashboardScreen.getNotificationPermissionScreen().clickNoAllowButton();
        dashboardScreen.getIdentifyVerificationScreen().clickLaterButton();
        
        PortfolioScreen portfolioScreen = dashboardScreen.getBottomNavigationBar().clickPortfolioLabel();
        String currentBalance = portfolioScreen.getCurrentBalanceValue();

        Assert.assertEquals(currentBalance, account.getCurrentBalance(), "Current balance is not as expected");
    }
}
