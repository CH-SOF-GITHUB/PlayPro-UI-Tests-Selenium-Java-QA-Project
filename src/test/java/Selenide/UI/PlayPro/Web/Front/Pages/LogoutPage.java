package Selenide.UI.PlayPro.Web.Front.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LogoutPage {
    // Define web elements using locators
    private final SelenideElement userProfileIcon = $(By.xpath("(//button[normalize-space()='Me déconnecter'])[1]"));

    //Method to click on Logout button
    public void clickLogoutButton() {
        userProfileIcon.click();
    }

}
