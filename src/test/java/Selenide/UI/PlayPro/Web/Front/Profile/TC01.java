package Selenide.UI.PlayPro.Web.Front.Profile;

import Selenide.Base;
import Selenide.UI.PlayPro.Web.Front.Pages.CookiePage;
import Selenide.UI.PlayPro.Web.Front.Pages.LoginPage;
import Selenide.UI.PlayPro.Web.Front.Pages.UserProfilePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class TC01 extends Base {

    // define an instances of page objects
    LoginPage loginPage = new LoginPage();
    CookiePage cookiePage = new CookiePage();
    UserProfilePage userProfilePage = new UserProfilePage();

    @BeforeMethod(alwaysRun = true)
    public void UserCanLoginWithValidCredentials() throws InterruptedException {
        // Step 1: Open the login page
        loginPage.openLoginPage();
        // Step 2: Click on the accept button of the cookie banner
        cookiePage.clickAcceptButton();
        // Step 2: Enter valid username and password
        loginPage.setEmail("demotenant3@yopmail.com");
        loginPage.setPassword("Admin1234!");
        // Step 3: Click on the login button
        loginPage.clickLoginButton();
        // Step 4: Verify that the user is successfully logged in and redirected to the dashboard
        webdriver().shouldHave(url("https://demotenant.playpro.fr/"));
    }

    @Test(priority = 1)
    public void UserClickOnUserProfileIcon() throws InterruptedException {
        // Step 1: Click on the user profile icon
        userProfilePage.clickUserProfileIcon();
        System.out.println("User clicked on profile icon");
    }
}
