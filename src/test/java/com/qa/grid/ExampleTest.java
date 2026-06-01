package com.qa.grid;

import com.qa.constants.Priority;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ExampleTest {

    // declare a remote web driver
    private RemoteWebDriver driver;
    // define explicit wait object
    WebDriverWait Wait;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        System.out.println("Setting up the test environment grid...");
        // Code to initialize WebDriver and other setup tasks
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new RemoteWebDriver(new URL("http://192.168.1.5:4444"), options);
        Wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(25));
        driver.manage().window().maximize();
        driver.get("https://demotenant.playpro.fr/");
        WebElement AcceptBtn = Wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='c15t-button-YKOgW c15t-button-small-n5LJg c15t-button-primary-stroke-TWzjH'][normalize-space()='Accepter'])[1]")));
        AcceptBtn.click();
        System.out.println("WebDriver initialized successfully.");
    }

    @Test(priority = Priority.P1)
    public void testExample() throws InterruptedException {
        System.out.println("Running the test...");
        // Code to perform test actions and assertions
        WebElement PanierIcon = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='relative']")));
        PanierIcon.click();
        // sleep for 7 seconds to allow the page to load
        Thread.sleep(7000);
        String expectedURL = "https://demotenant.playpro.fr/Panier";
        String actualURL = driver.getCurrentUrl();
        // Junit assertion to compare expected and actual URLs
        Assert.assertEquals("Test failed and Urls panier are mismatch!", expectedURL, actualURL);
    }

    @Test(priority = Priority.P2, dependsOnMethods = {"testExample"})
    public void testExample2() throws InterruptedException {
        // click on Découvrir maintenant button
        WebElement DiscoverNowButton = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"container-id\"]/div/div[2]/div/a")));
        DiscoverNowButton.click();
        // sleep for 7 seconds to allow the page to load
        Thread.sleep(7000);
        String expectedURL = "https://demotenant.playpro.fr/discover/reservation";
        String actualURL = driver.getCurrentUrl();
        // Junit assertion to compare expected and actual URLs
        Assert.assertEquals("Test failed and Urls discover/reservation are mismatch!", expectedURL, actualURL);
    }

    @Test(priority = Priority.P3, dependsOnMethods = {"testExample2"})
    public void testExample3() throws InterruptedException {
        // click on Expérience or activité for reservation
        WebElement ExperienceCard = Wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/section[1]/section[1]/div[2]/div[4]/div/div[2]")));
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].scrollIntoView(true);", ExperienceCard);
        js.executeScript("arguments[0].click();", ExperienceCard);
        System.out.println("Clicked on the Experience card successfully.");
        // sleep for 7 seconds to allow the page to load
        Thread.sleep(7000);
        String expectedURL = "https://demotenant.playpro.fr/discover/reservation/vrdemochaker-copie";
        String actualURL = driver.getCurrentUrl();
        // Junit assertion to compare expected and actual URLs
        Assert.assertEquals("Test failed and Urls Experience/reservation are mismatch!", expectedURL, actualURL);
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Tearing down the test environment grid...");
        // Code to quit WebDriver and clean up resources
        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver quit successfully.");
        }
    }
}
