package com.qa.cucumber.hooks;
import com.qa.cucumber.Utility.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        Base.getDriver();   // crée le driver, applique le timeout, ouvre l'URL
        System.out.println("Starting : " + scenario.getName());
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (Base.driver != null) {
            if (scenario.getStatus() == Status.PASSED) {
                System.out.println("Test Pass : " + scenario.getName());
            } else if (scenario.isFailed()) {
                System.out.println("Test Failed : " + scenario.getName());
                byte[] shot = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.BYTES);
                // Attache l'image directement dans le rapport Cucumber
                scenario.attach(shot, "image/png", "failed screenshot");
            }
            Base.driver.quit();
            Base.driver = null;
        }
    }

    @Before(value = "@api", order = 1)
    public void apiSetup() {
    }
}
