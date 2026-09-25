package com.qa.tests.TestNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        System.out.println("================ Suite Of Test Started : '" + context.getName() + "' ================");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("================ Suite Of Test Finished : '" + context.getName() + "' ================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("============ Test Started : '" + result.getMethod().getMethodName() + "' ============");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✔️ Test Passed : '" + result.getMethod().getMethodName() + "'");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed : '" + result.getMethod().getMethodName() + "'");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }
}
