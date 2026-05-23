package tests.PlayPro.DEV.LambdaTest.Chrome.Login;

import PageObject.WebLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.PlayPro.DEV.BaseTest;

import java.util.Objects;

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
            // verify the login conditions
            Wait.until(d -> Objects.equals(d.getCurrentUrl(), "https://demotenant.playpro.fr/"));
            Wait.until(d -> Objects.equals(d.getTitle(), "DEMO TENANT"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SampleErrorLogin() {
        try {
            webLoginPage = new WebLoginPage(driver);
            webLoginPage.EnterEmail("demotenant1@yopmail.com");
            webLoginPage.EnterPassword("Admin12345!");
            webLoginPage.ClickLoginButton();
            // verify the error login is appeared
            String ErrorMsg = webLoginPage.GetLoginErrorMessage();
            System.out.println(ErrorMsg);
            Assert.assertEquals(ErrorMsg, "Veuillez vérifier votre email/ mot de passe", "Error Login Failed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
