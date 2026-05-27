package Selenide.UI.PlayPro.Web.Front.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CookiePage {
    // Define web elements using locators
    // SelenideElement acceptButton = $(By.xpath("//*[@data-testid = 'cookie-banner-footer-sub-group']//*[@data-testid = 'cookie-banner-accept-button']"));
    private final SelenideElement acceptBtn = $(By.cssSelector("div[class='c15t-footerSubGroup-HbTp3'] button[class='c15t-button-YKOgW c15t-button-small-n5LJg c15t-button-primary-stroke-TWzjH']"));

    // Define method to click on the accept button
    public void clickAcceptButton() {
        acceptBtn.click();
    }

}
