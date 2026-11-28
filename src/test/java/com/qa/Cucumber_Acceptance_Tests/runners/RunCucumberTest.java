package com.qa.Cucumber_Acceptance_Tests.runners;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectDirectories;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.JUNIT_PLATFORM_NAMING_STRATEGY_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * JUnit 5 entry point for the Cucumber acceptance tests.
 *
 * <p>Run with {@code mvn test -Pjunit5}. The {@code Test} suffix makes the class
 * discoverable by Maven Surefire's default includes. Only scenarios tagged
 * {@code @acceptedUsers} are executed.</p>
 */
@Suite
@SuiteDisplayName("Cucumber Acceptance Tests (JUnit 5)")
@IncludeEngines("cucumber")
@SelectDirectories("src/test/java/com/qa/Cucumber_Acceptance_Tests/features")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "com.qa.Cucumber_Acceptance_Tests.steps, com.qa.Cucumber_Acceptance_Tests.hooks"
)
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@acceptedUsers")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty,html:target/cucumber-acceptance-report.html,json:target/cucumber-acceptance-report.json,junit:target/cucumber-acceptance-report.xml"
)
@ConfigurationParameter(key = JUNIT_PLATFORM_NAMING_STRATEGY_PROPERTY_NAME, value = "long")
public class RunCucumberTest {
}
