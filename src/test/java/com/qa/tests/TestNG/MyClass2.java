package com.qa.tests.TestNG;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class MyClass2 {
    @Test
    public void myMethod3() {
        Reporter.log("Reporter - MyClass2 - Inside ....... myMethod3" + true);
    }

    @Test
    public void myMethod4() {
        Reporter.log("Reporter - MyClass2 - Inside ....... myMethod4" + true);
        Assert.assertEquals(true, true, "not equal");
    }
}
