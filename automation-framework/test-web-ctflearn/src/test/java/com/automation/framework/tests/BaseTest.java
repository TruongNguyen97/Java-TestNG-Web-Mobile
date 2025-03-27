package com.automation.framework.tests;
import java.io.IOException;
import java.util.Map;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.automation.framework.core.reports.ReportManager;
import com.automation.framework.core.utils.ConfigReader;
import com.automation.framework.core.utils.JsonUtil;
import com.automation.framework.core.webdrivers.DriverExtensions;
import com.automation.framework.core.webdrivers.DriverManager;
import com.automation.framework.models.Account;
import com.automation.framework.pages.HomePage;
import com.automation.framework.utils.Constants;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


public class BaseTest{
    protected HomePage homePage;
    protected Map<String, Account> accountDataMap;
    
    public BaseTest() throws JsonParseException, JsonMappingException, IOException {
        accountDataMap = JsonUtil.readJsonFileToObject(Constants.LOGIN_DATA_FILE_PATH, Account.class);
        homePage = new HomePage();
    }

    @BeforeSuite
    public void beforeSuite() throws IOException {
        System.out.println("BeforeSuite: Initialize global resources (e.g., Selenium Grid, reports)");
        ConfigReader.initConfigReader(Constants.CONFIG_FILE_PATH);
        ReportManager.initReport(ConfigReader.getProperty("report.path"));
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
        DriverManager.initDriver();
        DriverExtensions.openUrl(ConfigReader.getProperty("web.url"));
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
            forceLogout();
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
        DriverManager.quitDriver();
    }

    public void forceLogout() {
        DriverExtensions.openUrl(ConfigReader.getProperty("web.url") + Constants.LOGOUT_END_POINT);
        homePage.waitPageLoad();
    }
}
