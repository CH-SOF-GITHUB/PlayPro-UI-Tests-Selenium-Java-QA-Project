package tests.PlayPro.DEV.LambdaTest.Chrome.Login;

import PageObject.WebLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.PlayPro.DEV.BaseTest;

public class TC01 extends BaseTest {
    // define Login object page
    WebLoginPage webLoginPage = null;

    @Test
    public void SampleLogin() {
        try {
            webLoginPage = new WebLoginPage(driver);
            webLoginPage.EnterEmail("demotenant1@yopmail.com");
            webLoginPage.EnterPassword("Admin1234!");
            webLoginPage.ClickLoginButton();
            // wait for 7s for loading page
            Thread.sleep(7000);
            // verify the login conditions
            String ActualURL = driver.getCurrentUrl();
            String ActualTitle = driver.getTitle();
            Assert.assertEquals(ActualURL, "https://demotenant.playpro.fr/");
            Assert.assertEquals(ActualTitle, "DEMO TENANT");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SampleErrorLogin() {
        try {
            webLoginPage = new WebLoginPage(driver);
            webLoginPage.EnterEmail("demotenant1@yopmail.com");
            webLoginPage.EnterPassword("Admin1234!");
            webLoginPage.ClickLoginButton();
            // verify the error login is appeared
            String ErrorMsg = webLoginPage.GetLoginErrorMessage();
            Assert.assertEquals(ErrorMsg, "Veuillez vérifier votre email/ mot de passe", "Error Login Failed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
