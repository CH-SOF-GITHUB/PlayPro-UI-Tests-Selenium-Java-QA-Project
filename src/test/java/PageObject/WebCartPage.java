package PageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// page_url = https://chakertestqa.playpro.fr/connexion
public class WebCartPage {
    // define static web driver
    protected WebDriver driver;
    // define Explicit time out for web elements
    protected WebDriverWait wait;

    public WebCartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }


    /**
     *  Card & Payment Buttons
     */
    @FindBy(xpath = "(//span[@class='md:w-5 w-4 md:h-5 h-4 border-2 border-black rounded-full'])[1]")
    private WebElement BankCardBtn;

    @FindBy(xpath = "(//span[@class='relative rounded-lg w-[17px] h-[17px] border-2 bg-white'])[1]")
    private WebElement VisaCard11_26Btn;

    @FindBy(xpath = "(//button[normalize-space()='Payer maintenant'])[1]")
    private WebElement PayNowBtn;

    @FindBy(xpath = "(//p[@class='font-poppins font-semibold text-black lg:text-xl text-sm py-4 text-center leading-4'])[1]")
    private WebElement OrderConfirmationMessage;

    /** Methods of POM class: Card & Payment Buttons
     */
    public void clickBankCardBtn() throws InterruptedException {
        try {
            // wait for 7s
            Thread.sleep(7000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Scroll down to the experience card
            js.executeScript("window.scrollBy(0, 300);");
            wait.until(ExpectedConditions.elementToBeClickable(BankCardBtn));
            BankCardBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickVisaCard11_26Btn() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(VisaCard11_26Btn));
            VisaCard11_26Btn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickPayNowBtn() {
        try {
            Thread.sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Scroll down to the experience card
            for (int i = 0; i < 2; i++) {
                js.executeScript("window.scrollBy(0, 300);");
                Thread.sleep(1000);
            }
            wait.until(ExpectedConditions.elementToBeClickable(PayNowBtn));
            PayNowBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
            }
    }

    public String GetOrderConfirmationMessage() {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(OrderConfirmationMessage));
            return OrderConfirmationMessage.getText();
        } catch (Exception e) {
            e.fillInStackTrace();
            return null;
        }
    }
}
