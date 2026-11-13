package com.qa.cucumber.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.simple.JSONObject;                    // bibliothèque A (org.json)
import org.json.simple.parser.JSONParser;      // bibliothèque B (json-simple)

import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;

public class Base {
    public static WebDriver driver;
    static WebDriverWait wait;
    static File f1 = new File("./JSON/Configuration.json");

    public static WebDriver getDriver() {
        JSONObject jsonObject = JSONReadFromFile();
        String browser = ((String) jsonObject.get("browser")).trim();
        String base_url = (String) jsonObject.get("url");
        int timeout = ((Long) jsonObject.get("timeout")).intValue();

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Browser '" + browser + "' is not supported");
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        driver.manage().window().maximize();
        driver.get(base_url);
        return driver;
    }

    // Actions base methods
    public static List<WebElement> getList(List<WebElement> elements) {
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }

    public static boolean elementToBeVisible(WebDriver driver, WebElement element) {
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            flag = true;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return flag;
    }

    public static boolean alertIsPresent(WebDriver driver) {
        boolean flag = false;
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            flag = true;
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return flag;
    }

    public static boolean elementFound(WebDriver driver, int time, WebElement element) {
        boolean res = false;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        try {
            res = element.isDisplayed();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return res;
    }

    public static boolean elementFound(WebElement element) {
        boolean res = false;
        try {
            res = element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String getText(WebElement element) {
        String name = null;
        try {
            if (elementFound(element)) {
                name = element.getText();
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return name;
    }

    public static void setText(WebElement element, String name) {
        try {
            if (name != null && elementFound(element)) {
                element.clear();
                element.sendKeys(name);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public static void clickBtn(WebElement element) {
        try {
            if (elementFound(element)) {
                element.click();
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    //
    private static JSONObject JSONReadFromFile() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(f1.getAbsoluteFile()));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
