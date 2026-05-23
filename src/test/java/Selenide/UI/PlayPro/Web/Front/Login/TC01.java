package Selenide.UI.PlayPro.Web.Front.Login;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class TC01 {


    @Test
    public void UserCanLoginWithValidCredentials() {
        // Step 1: Open the login page
        open("");
        // Step 2: Enter valid username and password
        // Step 3: Click on the login button
        // Step 4: Verify that the user is successfully logged in and redirected to the dashboard
    }
}
