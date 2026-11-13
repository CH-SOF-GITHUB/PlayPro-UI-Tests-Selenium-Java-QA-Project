package com.qa.cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/SwagLabs",
        glue = {"com.qa.cucumber.stepdefs", "com.qa.cucumber.hooks"},
        tags = "@smoke and @negative",    // Profiles > Run/Debug Configurations > Cucumber Options > Tags
        plugin = {"pretty", "html:target/cucumber-report.html", "json:target/cucumber.json"},
        monochrome = true,
        dryRun = false
)

@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}


/*
plugin = {"me.jvt.cucumber.report.PrettyReports:target/cucumber"} to generate the cucumber report in target/cucumber folder
is eliminated for now: net.masterthought.cucumber.ValidationException: Passed files have no features!
public class TestRunner extends AbstractTestNGCucumberTests {
}
tags = "@dev" to run only the scenarios with @dev tag in the feature file, if I not put tag in feature file , no tests will be executed

create a File Of Test Runner for Cucumber Scenarios  with [Junit + Java]
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
*/
