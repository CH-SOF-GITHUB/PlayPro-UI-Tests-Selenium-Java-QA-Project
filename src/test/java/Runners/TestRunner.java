package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/demov3/goToReservationPage.feature",
        glue = {"BDD"},
        plugin = { "me.jvt.cucumber.report.PrettyReports:target/cucumber" }
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
