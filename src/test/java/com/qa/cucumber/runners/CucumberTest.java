package com.qa.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;


@Test
@CucumberOptions(
        features = "src/test/resources/features/EventHUBApp/login_invalid_email.feature",
        glue = "com.qa.cucumber.bdd.EVENTHUB",
        plugin = {
                "json:target/cucumber-report.json",
                "html:target/cucumber-report.html"
        }
)
public class CucumberTest extends AbstractTestNGCucumberTests {
}
