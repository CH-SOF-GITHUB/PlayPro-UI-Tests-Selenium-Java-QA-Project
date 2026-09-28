package com.qa.tests.TestNG;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ClassTestForCustomReporter {
    @Test
    public void myMethod1() {
        Reporter.log("ClassTestForCustomReporter - Inside ....... myMethod1" + true);
    }

    @Test
    public void myMethod2() {
        Reporter.log("ClassTestForCustomReporter - Inside ....... myMethod2" + true);
        Assert.assertEquals(false, true, "not equal");
    }

    @Test
    public void myMethod3() {
        Reporter.log("ClassTestForCustomReporter - Inside ....... myMethod3" + true);
    }

    @Test(enabled = false)
    public void myMethod4() {
        Reporter.log("ClassTestForCustomReporter - Inside ....... myMethod4" + true);
    }
}
