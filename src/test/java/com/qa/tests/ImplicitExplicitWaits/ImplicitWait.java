package com.qa.tests.ImplicitExplicitWaits;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImplicitWait {
    WebDriver driver;

    // @BeforeSuite: The annotation method will be run before all tests in this suite have run.
    @BeforeSuite
    public void configureLogs() {
        // AJOUT : masquer les logs Selenium INFO/WARNING
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.devtools").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.chromium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);
    }

    // @BeforeMethod: The annotation method will be run before each test method.
    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    // @AfterMethod: The annotation method will be run after each test method.
    @AfterMethod
    public void end() {
        driver.close();
    }

    @Test
    public void test1() throws InterruptedException {
        driver.get("https://eventhub.rahulshettyacademy.com/");
        signIn("bchaker28@yahoo.com", "Q5n@j!i!QnZQmYm");
        //driver.findElement(By.xpath("//button[normalize-space()='My Bookings']")).click();
        Thread.sleep(5000);
        System.out.println("Inside EventHUB Page");
    }

    public void signIn(String email, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);

        WebElement SignInButton = driver.findElement(By.xpath("(//button[normalize-space()='Sign In'])[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SignInButton);
    }
}
