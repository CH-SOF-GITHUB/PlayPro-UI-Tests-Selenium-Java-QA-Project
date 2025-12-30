package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/demov3/sampleLogin.feature",
        glue = {"BDD"},
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
