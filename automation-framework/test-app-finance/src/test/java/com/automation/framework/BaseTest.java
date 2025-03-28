package com.automation.framework;

import java.io.IOException;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.automation.framework.core.mails.MailslurpClient;
import com.automation.framework.core.mobiledrivers.MobileDriverManager;
import com.automation.framework.core.reports.ReportManager;
import com.automation.framework.core.utils.ConfigReader;
import com.automation.framework.core.utils.JsonUtil;
import com.automation.framework.helpers.MailHelper;
import com.automation.framework.models.Account;
import com.automation.framework.pages.HomePage;
import com.automation.framework.utils.Constants;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;




public class BaseTest {
    protected HomePage homePage;
    protected MailHelper mailHelper;
    protected Map<String, Account> accountDataMap;
    
    public BaseTest() throws JsonParseException, JsonMappingException, IOException {
        ConfigReader.initConfigReader(Constants.CONFIG_FILE_PATH);
        accountDataMap = JsonUtil.readJsonFileToObject(Constants.LOGIN_DATA_FILE_PATH, Account.class);
        homePage = new HomePage();
        mailHelper = new MailHelper(new MailslurpClient(ConfigReader.getProperty("mailslurp.api.key")));

        // mailHelper.deleteAllEmailOfAccount(accountDataMap.get("account_new_created_user"));
        // mailHelper.getVerificationCodeFromLastestEmail(accountDataMap.get("account_new_created_user"));
    }

    @BeforeSuite
    public void beforeSuite() throws IOException {
        System.out.println("BeforeSuite: Initialize global resources (e.g., Selenium Grid, reports)");
        ReportManager.initReport(ConfigReader.getProperty("report.path"));
        // AppiumServerManager.startServer();
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite: Clean up global resources");
        // Generate reports
        ReportManager.flushReport();
    }

    @BeforeTest
    public void setUpTest() {
        System.out.println("BeforeMethod: Set up for each test method");
        MobileDriverManager.initDriver();
        ReportManager.createExtentTest(this.getClass().getSimpleName());
    }

    @BeforeMethod
    public void setUp(ITestResult result) {
        ReportManager.createExtentNode(result.getMethod().getMethodName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Update the test result to the report
        ExtentTest extentNode = ReportManager.getExtentNode();
 
        if (result.getStatus() == ITestResult.FAILURE) {
            extentNode.fail("-> Failed with error: " + result.getThrowable().getMessage());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentNode.pass("-> Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentNode.skip("-> skipped");
        }
    }

    @AfterTest
    public void tearDownTest() {
        System.out.println("AfterMethod: Tear down for each test method");
        // Quit WebDriver or clean up resources
        MobileDriverManager.quitDriver();
        // AppiumServerManager.stopServer();
    }

}
