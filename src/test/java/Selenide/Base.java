package Selenide;

import static com.codeborne.selenide.Selenide.webdriver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.TextCheck;
import org.junit.Rule;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.codeborne.selenide.testng.ScreenShooter;
// The real issue: ScreenShooter is NOT in selenide.junit anymore (Selenide 7.x)
// import com.codeborne.selenide.junit4.ScreenShooter;
// Use Listeners for TestNG
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.webdriver;

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
        // Add configuration chrome options
        ChromeOptions options = new ChromeOptions();
        // REAL Maximize
        options.addArguments("--start-maximized");
        // Run tests in headless mode (without opening a browser window)
        /* Detect github actions
        sert à lire une variable d’environnement du système.
        Sur GitHub Actions, GitHub ajoute automatiquement :
        GITHUB_ACTIONS=true */
        String githubActions = System.getenv("GITHUB_ACTIONS");
        if (githubActions != null && githubActions.equals("true")) {
            Configuration.headless = true;
            System.out.println("GITHUB_ACTIONS detected: " + githubActions);
            System.out.println("Running in headless mode on GitHub Actions.");
        } else {
            Configuration.headless = false;
            System.out.println("GITHUB_ACTIONS detected: " + githubActions);
            System.out.print("Running in normal mode on local machine.");
        }
        // set chrome capabilites;
        Configuration.browserCapabilities = options;
        // Timeouts
        Configuration.pageLoadTimeout = 10000;
        Configuration.timeout = 10000;
        Configuration.webdriverLogsEnabled = true;
        // TextCheck - to avoid "Element should have text '...' but actual text was '...'"
        Configuration.textCheck = TextCheck.FULL_TEXT;
        // Reports Folder
        Configuration.reportsFolder = "src/test/java/Selenide/UI/reports";
        // Screenshot Folder
        Configuration.downloadsFolder = "src/test/java/Selenide/UI/downloads";
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
