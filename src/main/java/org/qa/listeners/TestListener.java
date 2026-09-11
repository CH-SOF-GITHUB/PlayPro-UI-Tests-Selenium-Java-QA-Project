package org.qa.listeners;

import org.qa.base.BaseClass;
import org.qa.utilities.ExtentManager;
import org.qa.utilities.RetryAnalyser;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Classe d'écouteur de tests TestNG.
 * Surcharge des méthodes clés pour capturer les résultats des tests.
 */
public class TestListener implements ITestListener, IAnnotationTransformer {

    // USE IAnnotationTransformer interface for Re-Try Tests execution:
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyser.class);
    }

    // Triggered when a suite starts
    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test suite started: " + context.getName());
        ExtentManager.getReporter(); // Initialize the ExtentReports
    }

    // Triggered when a suite finishes
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test suite finished: " + context.getName());
        ExtentManager.endTest();
    }

    // Triggered when a test start
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        // Start logging in Extent Reports
        ExtentManager.startTest(testName);
        ExtentManager.logStep("Test Started - " + testName);
    }

    // Triggered when a test success
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        // Send success results in Extent Reports
        if (!result.getTestClass().getName().toLowerCase().contains("api")) {
            ExtentManager.logStepWithScreenshot(BaseClass.getDriver(), "Test Passed", "Test End: " + testName + " - ✔️ Test Passed");
        } else {
            ExtentManager.logStepForApi("Test API End: " + testName + " - ✔️ Test Passed");
        }
    }

    // Triggered when a test fails
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        // Send success results in Extent Reports
        if (!result.getTestClass().getName().toLowerCase().contains("api")) {
            ExtentManager.logFailureWithScreenshot(BaseClass.getDriver(), "Test Failed: " + result.getThrowable().getMessage(), "Test End: " + testName + " - ❌ Test Failed");
        } else {
            ExtentManager.logFailureForApi("Test API End: " + testName + " - ❌ Test Failed:  " + result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        // Send success results in Extent Reports
        ExtentManager.logSkip("Test Skipped - " + testName + " - ⚠️ Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + result.getName());
    }
}
