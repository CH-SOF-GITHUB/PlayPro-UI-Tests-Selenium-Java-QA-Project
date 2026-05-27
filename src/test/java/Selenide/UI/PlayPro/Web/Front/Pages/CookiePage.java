package Selenide.UI.PlayPro.Web.Front.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CookiePage {
    // Define web elements using locators
    // SelenideElement acceptButton = $(By.xpath("//*[@data-testid = 'cookie-banner-footer-sub-group']//*[@data-testid = 'cookie-banner-accept-button']"));
    private final SelenideElement acceptBtn = $(By.xpath("/html/body/div[3]/div/div/div[4]/div/button[1]"));

    // Define method to click on the accept button
    public void clickAcceptButton() {
        acceptBtn.click();
    }

}
