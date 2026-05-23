package Selenide;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.TextCheck;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Base {

    @BeforeClass
    public void setUp() {
        System.out.println("Setting up Base Selenide configuration...");
        Configuration.baseUrl = "https://demotenant.playpro.fr/";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.pageLoadTimeout = 10000;
        Configuration.timeout = 10000;
        Configuration.webdriverLogsEnabled =true;
        Configuration.textCheck = TextCheck.FULL_TEXT;
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Tearing down Base Selenide configuration...");
    }
}
