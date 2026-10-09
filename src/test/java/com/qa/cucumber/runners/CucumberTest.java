package com.qa.cucumber.runners;

import com.qa.cucumber.bdd.Hooks;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


@Test
@CucumberOptions(
        features = "src/test/resources/features/Rahulshettyacademy/book_new_event.feature",
        glue = "com.qa.cucumber.bdd",
        plugin = {
                "json:target/cucumber-report.json",
                "html:target/cucumber-report.html"
        }
)
public class CucumberTest extends AbstractTestNGCucumberTests {
    // AJOUT : récupérer le paramètre browser défini dans testng.xml
    @BeforeClass
    @Parameters("browser")
    public void configureBrowser(String browser) {
        // AJOUT : afficher le navigateur reçu pour faciliter le debugging
        System.out.println("Browser received from TestNG: " + browser);
        // AJOUT : transmettre le browser au Hook Cucumber
        Hooks.setBrowser(browser);
    }
}
