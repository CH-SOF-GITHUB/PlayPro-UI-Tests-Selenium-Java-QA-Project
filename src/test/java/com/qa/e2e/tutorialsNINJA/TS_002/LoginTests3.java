package com.qa.e2e.tutorialsNINJA.TS_002;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.qa.utilities.LTStatus.addLambdaStepContext;

public class LoginTests3 extends BaseClass {
    // private RemoteWebDriver driver;
    // private String Status = "failed";


    @Test
    public void basicTest() throws InterruptedException {
        WebDriver driver = getDriver();

        System.out.println("Loading Url");

        addLambdaStepContext(driver, "Opening WebApp");
        driver.get("https://www.testmuai.com/selenium-playground/todo-app/");

        addLambdaStepContext(driver, "Checking List Items");
        System.out.println("Checking Boxes...");
        driver.findElement(By.name("li1")).click();
        driver.findElement(By.name("li2")).click();
        driver.findElement(By.name("li3")).click();
        driver.findElement(By.name("li4")).click();

        addLambdaStepContext(driver, "Adding Items");
        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 6");
        driver.findElement(By.id("addbutton")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 7");
        driver.findElement(By.id("addbutton")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 8");
        driver.findElement(By.id("addbutton")).click();

        addLambdaStepContext(driver, "Checking More Items");
        driver.findElement(By.name("li1")).click();
        driver.findElement(By.name("li3")).click();
        driver.findElement(By.name("li7")).click();
        driver.findElement(By.name("li8")).click();
        Thread.sleep(300);

        addLambdaStepContext(driver, "Adding and Verify List Items");
        driver.findElement(By.id("sampletodotext")).sendKeys("Get Taste of Lambda and Stick to It");
        driver.findElement(By.id("addbutton")).click();

        driver.findElement(By.name("li9")).click();

        String spanText = driver.findElement(By.xpath("//li[9]/span")).getText();
        Assert.assertEquals(spanText.trim(), "Get Taste of Lambda and Stick to It");

        //Status = "passed";
        Thread.sleep(150);

        System.out.println("Test Finished");
    }
}
