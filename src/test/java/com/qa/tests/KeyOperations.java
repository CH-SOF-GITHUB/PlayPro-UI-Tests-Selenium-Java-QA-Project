package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class KeyOperations {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/selectable/");
        driver.manage().window().maximize();

        driver.switchTo().frame(0);

        WebElement first = driver.findElement(By.cssSelector("ol#selectable>li:nth-child(1)"));
        WebElement third = driver.findElement(By.cssSelector("ol#selectable>li:nth-child(3)"));
        WebElement fifth = driver.findElement(By.cssSelector("ol#selectable>li:nth-child(5)"));

        Actions actions = new Actions(driver);
        // Click multiple items
        actions.keyDown(Keys.COMMAND).perform();
        actions.click(first);
        actions.click(third);
        actions.click(fifth);
        actions.keyUp(Keys.COMMAND).perform();
    }
}
