package PageObject.Activites;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// page_url = about:blank
public class WebEXP1Page {
    // define static web driver
    protected WebDriver driver;
    // define Explicit time out for web elements
    protected WebDriverWait wait;

    public WebEXP1Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    /**
     *  Experience Card Path
     */
    @FindBy(xpath = "(//p[normalize-space()='Nos activités'])[1]")
    private WebElement Nos_Activites_Text;

    @FindBy(xpath = "(//h3[normalize-space()='Vr Party Test'])[1]")
    private WebElement Vr_Party_Test;
    /**
     *  STEP 1 Reservation Selection buttons
     */
    @FindBy(xpath = "(//button[@id='menu-button'])[1]")
    private WebElement SelectParticipantsBtn;

    @FindBy(xpath = "(//button[normalize-space()='4 personnes'])[1]")
    private WebElement Option_4ParticipantsBtn;

    @FindBy(xpath = "(//button[normalize-space()='5 personnes'])[1]")
    private WebElement Option_5ParticipantsBtn;

    @FindBy(xpath = "(//button[normalize-space()='6 personnes'])[1]")
    private WebElement Option_6ParticipantsBtn;

    @FindBy(xpath = "(//button[normalize-space()='7 personnes'])[1]")
    private WebElement Option_7ParticipantsBtn;

    @FindBy(xpath = "(//button[normalize-space()='8 personnes'])[1]")
    private WebElement Option_8ParticipantsBtn;

    @FindBy(xpath = "(//button[normalize-space()='9 personnes'])[1]")
    private WebElement Option_9ParticipantsBtn;

    @FindBy(xpath = "(//button[normalize-space()='10 personnes'])[1]")
    private WebElement Option_10ParticipantsBtn;
    /**
     * STEP 2: Duration/Price buttons
     */
    @FindBy(xpath = "(//button[@id='menu-button'])[2]")
    private WebElement SelectDurationPriceBtn;

    @FindBy(xpath = "(//button[contains(text(),'45 min')])[1]")
    private WebElement Option_45MinBtn;

    @FindBy(xpath = "(//button[normalize-space()='90 min'])[1]")
    private WebElement Option_90MinBtn;

    @FindBy(xpath = "(//button[@class='w-full lg:w-1/3 h-[60px] rounded-md px-[15px] lg:text-lg lg:px-0 lg:py-1 py-[7px] gap-[10px] opacity-100 bg-primary text-accent'])[1]")
    private WebElement ContinueBtn;

    /**
     *  STEP 3 Time Slot Selection buttons
     */
    @FindBy(xpath = "(//button[@type='button'])[6]")
    private WebElement TimeSlot20_45Btn;

    @FindBy(xpath = "(//button[@type='button'])[5]")
    private WebElement TimeSlot20_00Btn;

    @FindBy(xpath = "(//button[@class='opacity-100 w-full lg:w-1/3 h-[60px] rounded-md px-[15px] py-[7px] gap-[10px] opacity-100 font-poppins font-medium text-[15px] leading-[21px] tracking-[0px] text-center bg-primary text-accent'])[1]")
    private WebElement ConfirmBtn;

    @FindBy(xpath = "(//button[normalize-space()='Continuer sans option'])[1]")
    private WebElement ContinueWithoutOptionBtn;

    /**
     *  Method of POM class: Experience Card Path
     */

    public void clickVrPartyTestCard() throws InterruptedException {
        // wait for 7S
        Thread.sleep(7000);
        // scroll down to the experience card
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down to the experience card
        for (int i = 0; i < 3; i++) {
            js.executeScript("window.scrollBy(0, 300);");
            Thread.sleep(1000);
        }
        wait.until(ExpectedConditions.elementToBeClickable(Vr_Party_Test));
        Vr_Party_Test.click();
    }

    /**
     *  Methods of POM class: STEP 1 Reservation Selection buttons
     */
    public void clickSelectParticipantsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(SelectParticipantsBtn));
        SelectParticipantsBtn.click();
    }

    public void clickOption4ParticipantsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(Option_4ParticipantsBtn));
        Option_4ParticipantsBtn.click();
    }

    public void clickOption5ParticipantsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(Option_5ParticipantsBtn));
        Option_5ParticipantsBtn.click();
    }

    public void clickOption6ParticipantsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(Option_6ParticipantsBtn));
        Option_6ParticipantsBtn.click();
    }

    public void clickOption7ParticipantsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(Option_7ParticipantsBtn));
        Option_7ParticipantsBtn.click();
    }

    public void clickOption8ParticipantsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(Option_8ParticipantsBtn));
        Option_8ParticipantsBtn.click();
    }

    public void clickOption9ParticipantsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(Option_9ParticipantsBtn));
        Option_9ParticipantsBtn.click();
    }

    public void clickOption10ParticipantsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(Option_10ParticipantsBtn));
        Option_10ParticipantsBtn.click();
    }

    /**
     *  Methods of POM class: STEP 2: Duration/Price buttons
     */

    public void clickSelectDurationPriceBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(SelectDurationPriceBtn));
        SelectDurationPriceBtn.click();
    }

    public void clickOption45MinBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(Option_45MinBtn));
        Option_45MinBtn.click();
    }

    public void clickOption90MinBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(Option_90MinBtn));
        Option_90MinBtn.click();
    }

    public void clickContinueBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(ContinueBtn));
        ContinueBtn.click();
    }

    /**
     *  Methods of POM class: STEP 3 Time Slot Selection buttons
     */
    public void clickTimeSlot20_00Btn() throws InterruptedException {
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(TimeSlot20_00Btn));
        TimeSlot20_00Btn.click();
    }

    public void clickTimeSlot20_45Btn() throws InterruptedException {
        Thread.sleep(10000);
        wait.until(ExpectedConditions.elementToBeClickable(TimeSlot20_45Btn));
        TimeSlot20_45Btn.click();
    }

    public void clickConfirmBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(ConfirmBtn));
        ConfirmBtn.click();
    }

    public void clickContinueWithoutOptionBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(ContinueWithoutOptionBtn));
        ContinueWithoutOptionBtn.click();
    }
}
