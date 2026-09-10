package com.qa.e2e.orangeHRM;

import org.qa.base.BaseClass;
import org.qa.utilities.ExtentManager;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class DummyClass extends BaseClass {


    @Test
    public void dummyTest() {
        // Lunch the test with Extent Report
        // ExtentManager.startTest("DummyTest 1 Test"); // This has been implemented in TestListener
        String ExpectedTitle = "OrangeHRM";
        ExtentManager.logStep("Verifying the Title");
        String ActualTitle = getDriver().getTitle();
        assert ExpectedTitle.equals(ActualTitle) : "Test Failed: Title does not match";
        System.out.println("Dummy Test Passed");
        ExtentManager.logSkip("Dummy Test Skipped");
        throw new SkipException("Skipping the Test as a part of Testing");
    }
}
