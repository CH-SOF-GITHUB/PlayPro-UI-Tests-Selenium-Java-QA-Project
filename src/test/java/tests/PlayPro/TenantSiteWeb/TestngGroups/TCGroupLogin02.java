package tests.PlayPro.TenantSiteWeb.TestngGroups;

import config.LTConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import tests.PlayPro.PageObject.WebLoginPage;

public class TCGroupLogin02 extends LTConfig {
    // declare web driver and other necessary variables for the test class
    RemoteWebDriver driver;
    // apache commons logging
    private static final Log log = LogFactory.getLog(TCGroupLogin02.class);
    // define Instances of POM classes if needed
    WebLoginPage webLoginPage;

    // declare a constructor of test class to initialize the test class
    public TCGroupLogin02() {
        // initialization code if needed
    }

    @BeforeGroups("WebLoginTests")
    public void setUp() {
        this.driver = getLTDriver();
        webLoginPage = new WebLoginPage(driver);
        log.info("WebDriver is setup for @Groups Tests and Page Object ...\n");
    }

    @Test(groups = "WebLoginTests")
    public void testEmptyCredentials() {
        try {
            // code to test login with empty credentials
            webLoginPage.GoToLoginPage();
            webLoginPage.EnterEmail("");
            webLoginPage.EnterPassword("");
            webLoginPage.ClickLoginButton();
            // check the testng results
            Thread.sleep(5000);
            String ExpectedEmailErrorMsg = "Adresse email est obligatoire";
            String ExpectedPasswordErrorMsg = "Mot de passe est obligatoire";
            String ActualEmailErrorMsg = webLoginPage.GetEmailRequiredErrorMessage();
            String ActualPasswordErrorMsg = webLoginPage.GetPasswordRequiredErrorMessage();
            Assert.assertEquals(ActualEmailErrorMsg, ExpectedEmailErrorMsg, "Empty email error msg Failed!");
            Assert.assertEquals(ActualPasswordErrorMsg, ExpectedPasswordErrorMsg, "Empty password error msg Failed!");
            // get screenshot
            webLoginPage.takeSnapShot(driver, "src/test/java/tests/Screenshots/TestngGroupEmptyCredentials.png");
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
