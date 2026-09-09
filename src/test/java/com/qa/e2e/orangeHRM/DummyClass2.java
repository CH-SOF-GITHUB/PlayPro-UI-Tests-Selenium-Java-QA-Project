package com.qa.e2e.orangeHRM;

import org.qa.base.BaseClass;
import org.testng.annotations.Test;

public class DummyClass2 extends BaseClass {


    @Test
    public void dummyTest2() {
        String ExpectedTitle = "OrangeHRM";
        String ActualTitle = getDriver().getTitle();
        assert ExpectedTitle.equals(ActualTitle) : "Test Failed: Title does not match";
        System.out.println("Dummy Test Passed");
    }
}
