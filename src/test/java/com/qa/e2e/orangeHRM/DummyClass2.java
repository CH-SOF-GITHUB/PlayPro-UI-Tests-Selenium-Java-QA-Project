package com.qa.e2e.orangeHRM;

import org.qa.base.BaseClass;
import org.qa.utilities.ExtentManager;
import org.testng.annotations.Test;

public class DummyClass2 extends BaseClass {


    @Test
    public void dummyTest2() {
        // Lunch the test with Extent Report
        // ExtentManager.startTest("DummyTest 2 Test"); // This has been implemented in TestListener
        String ExpectedTitle = "OrangeHRM";
        ExtentManager.logStep("Verifying the Title");
        String ActualTitle = getDriver().getTitle();
        assert ExpectedTitle.equals(ActualTitle) : "Test Failed: Title does not match";
        System.out.println("Dummy Test Passed");
        ExtentManager.logStep("Validation DummyTest 2 Successful");
    }
}
