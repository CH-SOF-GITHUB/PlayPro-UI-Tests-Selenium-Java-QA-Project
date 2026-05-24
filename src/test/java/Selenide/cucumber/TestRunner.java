package Selenide.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/org/selenide/examples/cucumber/login.feature",
        glue = "Selenide.cucumber.StepsDefinitions",
        plugin = {"pretty", "html:target/selenide/cucumber/reports/cucumber-html-report.html"},
        monochrome = true
)
public class TestRunner {

}
