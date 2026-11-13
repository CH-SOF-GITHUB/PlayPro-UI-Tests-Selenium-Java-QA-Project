BDD (Behavior Driven Development) means we describe how the software should behave in plain English.
Business people, developers and testers all understand it.

Cucumber is a free tool that runs tests written in Gherkin language.
A feature file (plain text) is linked to Java code with Step Definitions.
Works with Selenium, REST Assured, Appium and more. Languages: Java, JS, Ruby.
(Feature File ----> Step Definitions ----> Selenium/API Code ----> Result Report)

Why teams use it:
Everyone understands the tests. They are living documentation.
Clear rules before code starting. Fewer misunderstandings.
Scenarios are reusable. One step can be used many times.
Test results are easy to read for non-technical people.

Limits:
Extra-Layer to maintain. Not needed for small unit tests.
Bad steps become long and messy. Good writing needs practice.

BDD is a process (talk before you code). Cucumber is just a tool that supports BDD. Do not say they are the same thing.

User Story Format:
As a (role), I want (goal), so that (benefit).
Example, As a User, I want to log in, so I can see my orders.

Popular BDD tools: Cucumber (Java,JS), SpecFlow (.NET), Behave (Python).
A feature file can also be a requirement document for the team.

/*
package com.qa.cucumber.runner;
import com.qa.cucumber.hooks.Hooks;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
features = "src/test/resources/features/Rahulshettyacademy/book_new_event.feature",
glue = "com.qa.cucumber.stepdefs",
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
*/
------------------------------------------------------------------------------------------------------------------------
mvn test "-Dcucumber.filter.tags=@smoke"
mvn test "-Dcucumber.filter.tags=@negative"

mvn test -Dcucumber.filter.tags="@swagLabsLogin"
