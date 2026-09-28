package com.qa.tests.TestNG;

import org.qa.utilities.RetryAnalyser;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class RetryTestFailed {

    @Test(retryAnalyzer = com.qa.tests.TestNG.MyRetry.class)
    public void myMethod1() {
        Reporter.log("RetryTestFailed - Inside ....... myMethod1" + true);
    }

    @Test(retryAnalyzer = com.qa.tests.TestNG.MyRetry.class)
    public void myMethod2() {
        Reporter.log("RetryTestFailed - Inside ....... myMethod2" + true);
        Assert.assertEquals(false, true, "not equal");
    }
}
