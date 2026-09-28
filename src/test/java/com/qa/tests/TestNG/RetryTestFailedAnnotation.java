package com.qa.tests.TestNG;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class RetryTestFailedAnnotation {

    @Test
    public void myMethod1() {
        Reporter.log("RetryTestFailedAnnotation - Inside ....... myMethod1" + true);
    }

    @Test
    public void myMethod2() {
        Reporter.log("RetryTestFailedAnnotation - Inside ....... myMethod2" + true);
        Assert.assertEquals(false, true, "not equal");
    }

    @Test
    public void myMethod3() {
        Reporter.log("RetryTestFailedAnnotation - Inside ....... myMethod3" + true);
    }
}
