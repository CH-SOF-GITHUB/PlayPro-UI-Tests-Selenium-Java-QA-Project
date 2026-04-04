package Runners;

/*
create a File Of Test Runner for Cucumber Scenarios  with [Junit + Java]
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
*/

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/FrontSite/sampleLoginParameterType.feature",
        glue = {"BDD"},
        tags = "@dev",
        plugin = {"me.jvt.cucumber.report.PrettyReports:target/cucumber"}
)
/*
public class TestRunner extends AbstractTestNGCucumberTests {
}
* */
public class TestRunner {
}
