package tests.PlayPro.TenantSiteWeb.TestngGroups;

import config.LTConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import PageObject.WebLoginPage;


public class TCGroupLogin01 extends LTConfig {
    // declare web driver and other necessary variables for the test class
    RemoteWebDriver driver;
    // apache commons logging
    private static final Log log = LogFactory.getLog(TCGroupLogin01.class);
    // define Instances of POM classes if needed
    WebLoginPage webLoginPage;

    // declare a constructor of test class to initialize the test class
    public TCGroupLogin01() {
        // initialization code if needed
    }

    @BeforeGroups("WebLoginTests")
    public void setUp() {
        this.driver = getLTDriver();
        webLoginPage = new WebLoginPage(driver);
        log.info("WebDriver is setup for @Groups Tests and Page Object ...\n");
    }

    // define test methods for login functionality
    @Test(groups = {"WebLoginTests"})
    public void testValidLogin() {
        try {
            // code to test valid login scenario
            webLoginPage.GoToLoginPage();
            webLoginPage.EnterEmail("chTenant01@yopmail.com");
            webLoginPage.EnterPassword("Admin1234!");
            webLoginPage.ClickLoginButton();
            // check the testng results
            Thread.sleep(5000);
            String ExpectedURL = "https://chakertestqa.playpro.fr/";
            String ActualURL = driver.getCurrentUrl();
            Assert.assertEquals(ActualURL, ExpectedURL, "Login Failed!");
            // get screenshot
            webLoginPage.takeSnapShot(driver, "src/test/java/tests/Screenshots/TestngGroupValidLogin.png");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test(groups = "WebLoginTests")
    public void testInvalidLogin() {
        try {
            // code to test invalid login scenario
            webLoginPage.GoToLoginPage();
            webLoginPage.EnterEmail("chTenant01@yopmail.com");
            webLoginPage.EnterPassword("Admin12345!");
            webLoginPage.ClickLoginButton();
            // check the testng results
            Thread.sleep(5000);
            String ExpectedErrorMsg = "Veuillez vérifier votre email/ mot de passe";
            String ActualErrorMsg = webLoginPage.GetLoginErrorMessage();
            Assert.assertEquals(ActualErrorMsg, ExpectedErrorMsg, "Login Failed!");
            // get screenshot
            webLoginPage.takeSnapShot(driver, "src/test/java/tests/Screenshots/TestngGroupInvalidLogin.png");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @AfterGroups("WebLoginTests")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            log.info("WebDriver is closed for Web Login Test-Suite ...\n");
        }
    }
}
