package Data_Driven.PlayProV3.InvalidLogin;

import Levels.Priority;
import PageObject.WebCookiesPage;
import PageObject.WebLoginPage;
import config.LTConfig;
import Data_Driven.testdata.DataProviderUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TC01 extends LTConfig {
    private static final Log log = LogFactory.getLog(TC01.class);
    // declare the web driver to control the browser
    RemoteWebDriver driver;
    // define Instances of POM classes if needed
    WebLoginPage webLoginPage;
    WebCookiesPage webCookiesPage;

    public TC01() {
    }

    @BeforeTest
    public void setUp() throws Exception {
        // get the driver of LambdaTest
        driver = getLTDriver();
        webLoginPage = new WebLoginPage(driver);
        webCookiesPage = new WebCookiesPage(driver);
        log.info("Driver setup successfully for Data-Driver Testing !\n");
    }

    @Test(
            dataProvider = "loginExcelData",
            dataProviderClass = DataProviderUtils.class,
            priority = Priority.P0
    )
    public void DisplayErrorMessages(String TUID, String email, String pwd, String ExpectedErrorMsg) {
        try {
            log.info("This is Test Case 01: Display Error Messages for Invalid Login Using Data Provider\n");
            // Write the code of test case here
            webLoginPage.GoToLoginPage();
            webCookiesPage.clickAcceptCookiesButton();
            webLoginPage.EnterEmail(email);
            Thread.sleep(1000);
            webLoginPage.EnterPassword(pwd);
            Thread.sleep(1000);
            webLoginPage.ClickLoginButton();
            Thread.sleep(2000);
            String ActualErrorMsg = webLoginPage.GetLoginErrorMessage() != null ? webLoginPage.GetLoginErrorMessage() : (webLoginPage.GetEmailRequiredErrorMessage() != null ? webLoginPage.GetEmailRequiredErrorMessage() : webLoginPage.GetPasswordRequiredErrorMessage());
            Assert.assertEquals(ActualErrorMsg, ExpectedErrorMsg, "error message not matched ! and test data-driven failed. ⛔");
            log.info("Data: " + TUID + " => error message matched successfully. ✅\n");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @AfterTest
    public void tearDown() throws Exception {
        // close the driver
        if (driver != null) {
            driver.quit();
            log.info("\nWeb Driven is closed and Data-Driven Test completed successfully !\n");
        }
    }
}
