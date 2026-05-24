package Selenide.UI.PlayPro.Web.Front.Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    // Define web elements using locators
    SelenideElement emailInput = $("input[name=email]");
    SelenideElement passwordInput = $("input[name=password]");
    SelenideElement loginButton = $("button[type=submit]");

    // OR Use Classic Page Object
    // @FindBy(how = How.NAME, using = "q")
    //  private SelenideElement searchBox;

    // Method to open the login page
    public void openLoginPage() {
        open("https://demotenant.playpro.fr/connexion");
    }

    // Method to set email
    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    // Method to set password
    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    // Method to click the login button
    public void clickLoginButton() {
        loginButton.click();
    }

}
