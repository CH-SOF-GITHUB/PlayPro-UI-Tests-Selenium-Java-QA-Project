package PageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

// page_url = about:blank
public class WebGiftVoucherPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public WebGiftVoucherPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    /* web elements of POM class */
    @FindBy(xpath = "(//span[contains(text(),'Offrir un bon cadeau')])[1]")
    private WebElement AddGiftVoucherBtn;

    @FindBy(xpath = "(//button[@type='button'])[11]")
    private WebElement Gift250Btn;

    @FindBy(xpath = "(//button[normalize-space()='Suivant'])[1]")
    private WebElement NextBtn;

    @FindBy(xpath = "(//span[@class='md:w-5 w-4 md:h-5 h-4 border-2 border-black rounded-full'])[1]")
    private WebElement BankCardBtn;

    @FindBy(xpath = "(//span[@class='relative rounded-lg w-[17px] h-[17px] border-2 bg-white'])[1]")
    private WebElement VisaCard11_26Btn;

    @FindBy(xpath = "(//button[normalize-space()='Payer maintenant'])[1]")
    private WebElement PayNowBtn;

    @FindBy(xpath = "(//p[@class='font-poppins font-semibold text-black lg:text-xl text-sm py-4 text-center leading-4'])[1]")
    private WebElement OrderConfirmationMessage;

    /* methods of POM class */
    public void clickAddGiftVoucherBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(AddGiftVoucherBtn));
        AddGiftVoucherBtn.click();
    }

    public void clickGift250Btn() {
        wait.until(ExpectedConditions.elementToBeClickable(Gift250Btn));
        Gift250Btn.click();
    }

    public void clickNextBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(NextBtn));
        NextBtn.click();
    }

    public void clickBankCardBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(BankCardBtn));
        BankCardBtn.click();
    }

    public void clickVisaCard11_26Btn() {
        wait.until(ExpectedConditions.elementToBeClickable(VisaCard11_26Btn));
        VisaCard11_26Btn.click();
    }

    public void clickPayNowBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,100)");
        wait.until(ExpectedConditions.elementToBeClickable(PayNowBtn));
        PayNowBtn.click();
    }

    public String GetOrderConfirmationMessage() {
        wait.until(ExpectedConditions.visibilityOf(OrderConfirmationMessage));
        return OrderConfirmationMessage.getText();
    }
}
