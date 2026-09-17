package org.qa.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.qa.utilities.ScreenshotUtil.takeScreenshot;

public class ExtentManager {

    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static String testName;

    public static void setTest(ThreadLocal<ExtentTest> test) {
        ExtentManager.test = test;
    }

    private static Map<Long, WebDriver> driverMap = new HashMap<>();

    /* Ajout
     * Stocke les éléments validés qui devront entourés
     * dans la capture finale du test.
     * */
    private static final ThreadLocal<List<By>> highlightedElements = ThreadLocal.withInitial(ArrayList::new);

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
        ExtentManager.testName = testName;
        // Ajout
        // clean up elements of previous test
        clearHighlightedElements();
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
        getTest().info(logMessage);
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

    /* Add an element to list highlightedElements */
    public static void addHighlightedElement(By by) {
        if (by != null && !highlightedElements.get().contains(by)) {
            highlightedElements.get().add(by);
        }
    }

    /* Add a Method to re-apply the borders before the final screenshot */
    public static void applyFinalBorders(WebDriver driver) {
        if (driver == null) {
            return;
        }
        for (By by : highlightedElements.get()) {
            try {
                WebElement element = driver.findElement(by);
                String script = "arguments[0].style.border = '3px solid green';";
                ((JavascriptExecutor) driver).executeScript(script, element);
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

    /* Clean up the list highlightedElements
    * */
    public static void clearHighlightedElements(){
        highlightedElements.get().clear();
        // clear the value associated to thread
        highlightedElements.remove();
    }

    // Register WebDriver for current Thread
    public static void registerDriver(WebDriver driver) {
        driverMap.put(Thread.currentThread().getId(), driver);
    }


    // Log a Failure For API
    public static void logFailureForApi(String failMessage) {
        // update color of log failure
        String colorMessage = String.format("<span style='color:red;'>%s</span>", failMessage);
        getTest().fail(colorMessage);
    }

    // Log a step For API
    public static void logStepForApi(String logMessage) {
        getTest().pass(logMessage);
    }

}
