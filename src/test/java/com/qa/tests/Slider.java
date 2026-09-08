package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Slider {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/slider/#colorpicker");
        driver.manage().window().maximize();

        driver.switchTo().frame(0);

        WebElement red = driver.findElement(By.cssSelector("div#red>span"));
        WebElement green = driver.findElement(By.cssSelector("div#green>span"));
        WebElement blue = driver.findElement(By.cssSelector("div#blue>span"));

        Actions actions = new Actions(driver);

        // Red slider
        actions.clickAndHold(red).moveByOffset(-100, 0).release(red).build().perform();
        Thread.sleep(2000);
        // Green  slider
        actions.clickAndHold(green).moveByOffset(-250, 0).release(green).build().perform();
        Thread.sleep(2000);
        // Blue slider
        actions.clickAndHold(blue).moveByOffset(-50, 0).release(blue).build().perform();
        Thread.sleep(2000);


        driver.quit();
    }
}
