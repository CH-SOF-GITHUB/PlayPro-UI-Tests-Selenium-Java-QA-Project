package com.qa.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;


@Test
@CucumberOptions(
        features = "src/test/resources/features/EventHUBApp/login.feature",
        glue = {"com.qa.cucumber.bdd"},
        plugin = {"json:target/cucumber.json",
                "html:target/cucumber-reports.html"}
)
public class CucumberTest extends AbstractTestNGCucumberTests {
}
