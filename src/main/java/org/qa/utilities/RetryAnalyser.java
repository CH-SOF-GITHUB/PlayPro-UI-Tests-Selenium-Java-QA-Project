package org.qa.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {

    // Number of retries
    private int retryCount = 0;
    // Declare maximum number of retires
    private static final int maxRetries = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetries) {
            retryCount++;
            return true; // Retry the test
        }
        return false;
    }
}
