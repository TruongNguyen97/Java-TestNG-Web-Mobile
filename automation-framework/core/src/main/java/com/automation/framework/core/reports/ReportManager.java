package com.automation.framework.core.reports;
import com.automation.framework.core.utils.StringUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ThreadLocal<ExtentTest> node = new ThreadLocal<>();

    public static void initReport(String filePath) {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(StringUtil.toAbsolutePath(filePath));
        extent.attachReporter(spark);
    }

    public static void createExtentTest(String testClassName) {
        test.set(extent.createTest(testClassName));
    }

    public static ExtentTest getExtentTest() {
        return test.get();
    }

    public static void createExtentNode(String testName) {
        node.set(getExtentTest().createNode(testName));
    }

    public static ExtentTest getExtentNode() {
        return node.get();
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
