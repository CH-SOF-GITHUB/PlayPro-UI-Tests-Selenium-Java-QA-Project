package Selenide;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.TextCheck;
import org.junit.Rule;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.codeborne.selenide.testng.ScreenShooter;
// The real issue: ScreenShooter is NOT in selenide.junit anymore (Selenide 7.x)
// import com.codeborne.selenide.junit4.ScreenShooter;
// Use Listeners for TestNG
import org.testng.annotations.Listeners;

// (TestNg )
@Listeners(ScreenShooter.class)
public class Base {
    // Take Screenshot For Tests (Junit 4 - even succeeded)
    // @Rule
    // public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();


    @BeforeClass
    public void setUp() {
        System.out.println("Setting up Base Selenide configuration...");
        Configuration.baseUrl = "https://demotenant.playpro.fr";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.pageLoadTimeout = 10000;
        Configuration.timeout = 10000;
        Configuration.webdriverLogsEnabled = true;
        Configuration.textCheck = TextCheck.FULL_TEXT;
        // Reports Folder
        Configuration.reportsFolder = "src/test/java/Selenide/reports";
        // Screenshot Folder
        Configuration.downloadsFolder = "src/test/java/Selenide/downloads";
        // Take Screenshot For Tests (TestNG - even succeeded)
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        ScreenShooter.captureSuccessfulTests = true;
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Tearing down Base Selenide configuration...");
    }
}
