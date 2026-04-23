package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

/*
create a File Of Test Runner for Cucumber Scenarios  with [Junit + Java]
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
*/

@CucumberOptions(
        features = "src/test/resources/features/FrontSite/Suite1",
        glue = {"BDD"},
        tags = "@dev",    // Profiles > Run/Debug Configurations > Cucumber Options > Tags
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber-reports.json"}
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
* */
