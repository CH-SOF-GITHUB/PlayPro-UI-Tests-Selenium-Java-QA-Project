package Selenide.UI.PlayPro.Web.Front.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UserProfilePage {
    // Define web elements using locators
    SelenideElement userProfileIcon = $(By.xpath("(//div[contains(@class,'relative')])[3]"));

    // Methods
    public void clickUserProfileIcon() {
        userProfileIcon.click();
    }

}
