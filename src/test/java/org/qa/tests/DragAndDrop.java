package org.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop {

    public static void main(String[] args) throws InterruptedException {
        // Define a selenium web driver
        WebDriver driver;
        // create a selenium web driver
        driver = new ChromeDriver();
        // define the mouse actions selenium class
        Actions actions = new Actions(driver);

        // Scenario: drag and drop element to another element
        driver.get("https://jqueryui.com/droppable/");
        driver.manage().window().maximize();

        // First, switch to iframe
        driver.switchTo().frame(0);

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement destination = driver.findElement(By.id("droppable"));

        // METHOD 1
        // actions.dragAndDrop(source, destination).perform();
        // METHOD 2
        actions.clickAndHold(source).moveToElement(destination).release(source).build().perform();

        System.out.println("drag completed");

        // close the browser
        driver.quit();
    }
}
