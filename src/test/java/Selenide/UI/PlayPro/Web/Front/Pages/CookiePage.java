package Selenide.UI.PlayPro.Web.Front.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CookiePage {
    // Define web elements using locators
    SelenideElement acceptButton = $(By.xpath("//*[@data-testid = 'cookie-banner-footer-sub-group']//*[@data-testid = 'cookie-banner-accept-button']"));

    // Define method to click on the accept button
    public void clickAcceptButton() {
        acceptButton.click();
    }

}
