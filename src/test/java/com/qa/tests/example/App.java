package com.qa.tests.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;
import org.qa.utilities.WaitClass;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    private static final WaitClass waitClass = new WaitClass();

    public static void main(String[] args) {
        System.out.println("==== Execute Tests on Local With .EXE ====");
        // Set the specific path to the ChromeDriver executable
        String chromedriverPath = "src/test/resources/drivers/chromedriver.exe";
        String firefoxdriverPath = "src/test/resources/drivers/geckodriver.exe";
        String edgedriverPath = "src/test/resources/drivers/msedgedriver.exe";
        String operadriverPath = "src/test/resources/drivers/operadriver.exe";
        String operaBinaryPath = "C:\\Users\\chaker\\AppData\\Local\\Programs\\Opera\\opera.exe";

        // Create driver
        WebDriver driver = createFireFoxDriver(firefoxdriverPath);

        // Open the website
        driver.get("https://bstackdemo.com/");
        driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(1280, 800));

        // Print the title of website
        String title = driver.getTitle();
        System.out.println("BstackDemo' Title : " + title);

        // Steps of scenario
        WebElement SignInBtn = driver.findElement(By.id("signin"));
        SignInBtn.click();

        // Sleep 3s and close the browser
        waitClass.sleep(5);

        driver.quit();
    }

    private static @NonNull WebDriver createChromeDriver(String chromedriverPath) {
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        ChromeOptions options = new ChromeOptions();
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(chromedriverPath)).build();
        return new ChromeDriver(service, options);
    }

    private static @NonNull WebDriver createFireFoxDriver(String firefoxdriverPath) {
        System.setProperty("webdriver.gecko.driver", firefoxdriverPath);
        FirefoxOptions options = new FirefoxOptions();
        FirefoxDriverService service = new GeckoDriverService.Builder().usingDriverExecutable(new File(firefoxdriverPath)).build();
        return new FirefoxDriver(service, options);
    }

    private static @NonNull WebDriver createEdgeDriver(String edgedriverPath) {
        System.setProperty("webdriver.edge.driver", edgedriverPath);
        EdgeOptions options = new EdgeOptions();
        // Optionnel mais recommandé pour éviter les conflits de ports en local
        options.addArguments("--remote-allow-origins=*");
        EdgeDriverService service = new EdgeDriverService.Builder().usingDriverExecutable(new File(edgedriverPath)).build();
        return new EdgeDriver(service, options);
    }

    /*private static @NonNull WebDriver createEdgeDriver(String edgedriverPath) {
        System.setProperty("webdriver.edge.driver", edgedriverPath);
        EdgeOptions options = new EdgeOptions();
        EdgeDriverService service = new EdgeDriverService.Builder().usingDriverExecutable(new File(edgedriverPath)).build();
        return new EdgeDriver(service, options);
    }*/

    // Safari WebDriver est fourni par Apple et fonctionne sur macOS.
    // Il n'est pas utilisable sur Windows.
    private static WebDriver createOperaDriver(String operaBinaryPath) {
        WebDriverManager.chromedriver().driverVersion("155.0.8059.85").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary(operaBinaryPath);
        return new ChromeDriver(options);
    }
    /*private static @NonNull WebDriver createOperaDriver(String operaBinaryPath) {
        // 1. Forcer WebDriverManager à télécharger exactement la version 155 (correspondant à votre Opera)
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().version("155.0.8059.85").setup();

        // 2. Configurer les options pour pointer vers votre binaire Opera
        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        if (operaBinaryPath != null && !operaBinaryPath.isEmpty()) {
            options.setBinary(operaBinaryPath);
        }

        // 3. Lancer le test
        return new org.openqa.selenium.chrome.ChromeDriver(options);
    }*/
    // Fix: Opera is Chromium-based

}
