package com.qa.tests.TestNG;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class MyClass {

    @Test
    public void myMethod1() {
        Reporter.log("Reporter - MyClass - Inside ....... myMethod1" + true);
    }

    @Test
    public void myMethod2() {
        Reporter.log("Reporter - MyClass - Inside ....... myMethod2" + true);
        Assert.assertEquals(false, true, "not equal");
    }
}
