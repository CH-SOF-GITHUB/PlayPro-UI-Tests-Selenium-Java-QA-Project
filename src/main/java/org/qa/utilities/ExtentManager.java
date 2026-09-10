package org.qa.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

import static org.qa.utilities.ScreenshotUtil.takeScreenshot;

public class ExtentManager {

    private static ExtentReports extent;
    @Getter
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void setTest(ThreadLocal<ExtentTest> test) {
        ExtentManager.test = test;
    }

    private static Map<Long, WebDriver> driverMap = new HashMap<>();

    // Initialize extent report
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/src/test/resources/extentReports/ExtentReports.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Orange HRM - Report");
            spark.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            // Adding system information to the report
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }

    // Start the test
    public synchronized static ExtentTest startTest(String testName) {
        ExtentTest extentTest = getReporter().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    // End the test
    public synchronized static void endTest() {
        getReporter().flush();
    }

    // Get current Thread's test
    public synchronized static ExtentTest getTest() {
        return test.get();
    }

    // Method to get the name of the current test
    public static String getTestName() {
        ExtentTest currentTest = getTest();
        if (currentTest != null) {
            return currentTest.getModel().getName();
        } else {
            return "No Test is currently active for this thread.";
        }
    }

    // Log a step
    public static void logStep(String logMessage) {
        getTest().info("Step executed in test: " + getTestName());
    }

    // Log a step validation with screenshot (Base64 embedded) - parameter removed
    public static void logStepWithScreenshot(WebDriver driver, String logMessage, String ScreenShotMessage) {
        getTest().pass(logMessage);
        // Screenshot method
        attachScreenshot64(driver, ScreenShotMessage);
    }

    // Log a Failure
    public static void logFailureWithScreenshot(WebDriver driver, String failMessage, String ScreenShotMessage) {
        // update color of log failure
        String colorMessage = String.format("<span style='color:red;'>%s</span>", failMessage);
        getTest().fail(colorMessage);
        // Screenshot method
        attachScreenshot64(driver, ScreenShotMessage);
    }

    // Log a Skip
    public static void logSkip(String logMessage) {
        // update color of log skip
        String colorMessage = String.format("<span style='color:orange;'>%s</span>", logMessage);
        getTest().skip(logMessage);
    }


    // Add a Method to attach a screenshot to report using Base64
    public synchronized static void attachScreenshot64(WebDriver driver, String message) {
        try {
            String screenshotBase64 = takeScreenshot(driver, getTestName());
            getTest().info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
        } catch (Exception e) {
            getTest().fail("Failed to attach screenshot: " + e.getMessage());
            e.fillInStackTrace();
        }
    }

    // Register WebDriver for current Thread
    public static void registerDriver(WebDriver driver) {
        driverMap.put(Thread.currentThread().getId(), driver);
    }
}
