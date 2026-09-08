package org.qa.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Resize_Element {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/resizable/");
        driver.manage().window().maximize();

        driver.switchTo().frame(0);

        WebElement source = driver.findElement(By.cssSelector("div.ui-icon-gripsmall-diagonal-se"));

        // Faire défiler en vue
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", source);

        Actions actions = new Actions(driver);
        actions.clickAndHold(source).moveByOffset(40, 250).build().perform();

        Thread.sleep(2000);
        driver.quit();
    }
}
