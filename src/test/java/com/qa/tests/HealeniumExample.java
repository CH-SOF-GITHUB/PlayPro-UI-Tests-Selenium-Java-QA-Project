package ai;

import com.epam.healenium.SelfHealingDriver;
import com.epam.healenium.annotation.DisableHealing;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Healenium (Self-Healing for Selenium)
 * Healenium automatically detects broken locators and self-heals them without manual updates.
 * It helps fix broken locators in Selenium by scripting self-healing AI-powered UI test automation.
 * If a locator fails, instead of the test failing when the UI changes,
 * Healenium dynamically finds it and repairs it, which reduces manual maintenance.
 */

public class HealeniumExample {
    public static void main(String[] args) {
        System.out.println("Hello, Healenium!");
        // Set up a regular web driver
        WebDriver delegate = new ChromeDriver();
        // Wrap the WebDriver with Healenium SelfHealing WebDriver
        WebDriver driver = SelfHealingDriver.create(delegate);
        // Open the website
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        // Find an element with Healenium self-healing
        WebElement Username = driver.findElement(By.name("username"));
        Username.sendKeys("Admin");
        WebElement Password = driver.findElement(By.name("password"));
        Password.sendKeys("admin123");
        WebElement button = driver.findElement(By.id("submit"));
        button.click();
        // Disable healing for a specific element
        // @DisableHealing
        WebElement staticElement = driver.findElement(By.id("staticElement"));
        // Close the browser
        driver.quit();
    }
}
