package com.qa.cucumber.hooks;

import com.qa.cucumber.Utility.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Hooks {

    //public static WebDriver driver;

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        Base.getDriver();   // crée le driver, applique le timeout, ouvre l'URL
        System.out.println("Starting : " + scenario.getName());
    }

    /*@Before(order = 0)
    public void setUp(Scenario scenario) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("Starting : " + scenario.getName());
    }*/

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
    /*@After
    public void tearDown(Scenario scenario) {
        if (scenario.getStatus().equals(Status.PASSED)) {
            System.out.println("Test Pass");
        } else if (scenario.isFailed()) {
            System.out.println("Test Failed");
            byte[] shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // Attach image directement dans le rapport cucumber
            scenario.attach(shot, "image/png", "failed screenshot");
        }
        driver.quit();
    }*/

    @Before(value = "@api", order = 1)
    public void apiSetup() {
    }

    //public WebDriver getDriver() {
    //    return driver;
    //}
}
