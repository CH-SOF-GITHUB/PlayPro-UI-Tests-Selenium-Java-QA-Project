package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/navigateReservation.feature",
        glue = {"StepDefinitions"},
        tags = "@NavigationToReservation",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
