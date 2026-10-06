package com.qa.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;


@Test
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.qa.cucumber.bdd.EVENTHUB",
        plugin = {
                "pretty",
                "json:target/cucumber-report.json" // Indispensable pour générer l'artefact lu par GitHub Actions
        }
)
public class CucumberTest extends AbstractTestNGCucumberTests {
}
