package com.qa.tests.TestNG;

import org.testng.TestNG;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RunFailedTestCases {


    @Test
    public void myFailedMethod() {
        // create an instance of TestNG
        TestNG runner = new TestNG();
        List<String> list = new ArrayList<>();
        list.add("C:\\Users\\chaker\\Desktop\\automation\\circleci-test\\PlayPro-UI-Tests-Selenium-Java-QA-Project\\test-output\\testng-failed.xml");
        runner.setTestSuites(list);
        runner.run();
    }
}
