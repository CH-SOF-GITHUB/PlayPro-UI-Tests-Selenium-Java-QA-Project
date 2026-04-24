package PageObject.Activites;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     *  Experience Card Path
     */
    /* @FindBy(xpath = "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' shadow-md ') and contains(concat(' ', normalize-space(@class), ' '), ' cursor-pointer ') and contains(concat(' ', normalize-space(@class), ' '), ' group ') and (@role = 'button') and (position() = 1)]//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' shadow-lg ')]")
    private WebElement Vr_Party_Test; */
    @FindBy(xpath = "//h3[normalize-space()='Vr Party Test']/ancestor::div[@role='button']")
    private WebElement Vr_Party_Test;

    /**
     *  STEP 1 Reservation Selection buttons
     */
    @FindBy(xpath = "//button[@id='menu-button']")
    private WebElement SelectParticipantsBtn;

    @FindBy(xpath = "//button[normalize-space()='4 personnes']")
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
    @FindBy(xpath = "//div[@class and contains(concat(' ', normalize-space(@class), ' '), ' relative ') and contains(concat(' ', normalize-space(@class), ' '), ' w-full ')]/*[@class and contains(concat(' ', normalize-space(@class), ' '), ' justify-bewteen ')]//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' font-poppins ') and contains(concat(' ', normalize-space(@class), ' '), ' text-sm ') and contains(concat(' ', normalize-space(@class), ' '), ' font-normal ') and contains(concat(' ', normalize-space(@class), ' '), ' text-left ')]")
    private WebElement SelectDurationPriceBtn;

    @FindBy(xpath = "(//button[contains(text(),'45 min')])[1]")
    private WebElement Option_45MinBtn;

    @FindBy(xpath = "(//button[normalize-space()='90 min'])[1]")
    private WebElement Option_90MinBtn;

    @FindBy(xpath = "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' rounded-md ')]")
    private WebElement ContinueBtn;

    /**
     *  STEP 3 Time Slot Selection buttons
     */
    // java
    @FindBy(xpath = "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' border ') and (position() = 4)]")
    private WebElement IncrementCalendarBtn;

    @FindBy(xpath = "//div[contains(@class,'relative')]//button//p")
    private WebElement Day__Btn;

    @FindBy(xpath = "(//button[@type='button'])[3]")
    private WebElement TimeSlot20_45_Btn;

    @FindBy(xpath = "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' relative ') and (name() = 'div') and (position() = 1)]//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' text-black ') and (@type = 'button')]//div[@class and contains(concat(' ', normalize-space(@class), ' '), ' justify-center ') and contains(concat(' ', normalize-space(@class), ' '), ' w-full ') and contains(concat(' ', normalize-space(@class), ' '), ' items-center ') and contains(concat(' ', normalize-space(@class), ' '), ' flex ')]")
    private WebElement TimeSlot20_00_Btn;


    /*        */
    /*    -------    */
    @FindBy(xpath = "//*[@class and contains(concat(' ', normalize-space(@class), ' '), ' rounded-md ') and contains(concat(' ', normalize-space(@class), ' '), ' bg-secondary ')]")
    private WebElement ConfirmBtn;

    @FindBy(xpath = "(//button[normalize-space()='Continuer sans option'])[1]")
    private WebElement ContinueWithoutOptionBtn;

    /**
     *  Method of POM class: Experience Card Path
     */

    public void clickVrPartyTestCard() throws InterruptedException {
        Thread.sleep(1000);
        WebElement card = wait.until(ExpectedConditions.visibilityOf(Vr_Party_Test));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", card);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", card);
        System.out.println("Vr Party Test card clicked");
    }

    /**
     *  Methods of POM class: STEP 1 Reservation Selection buttons
     */
    public void clickSelectParticipantsBtn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(SelectParticipantsBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Select Participants button clicked");
    }

    public void clickOption4ParticipantsBtn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(Option_4ParticipantsBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Option 4 Person  clicked");
    }

    public void clickOption5ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(Option_5ParticipantsBtn));
            wait.until(ExpectedConditions.elementToBeClickable(Option_5ParticipantsBtn));
            Option_5ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption6ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(Option_6ParticipantsBtn));
            wait.until(ExpectedConditions.elementToBeClickable(Option_6ParticipantsBtn));
            Option_6ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption7ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(Option_7ParticipantsBtn));
            wait.until(ExpectedConditions.elementToBeClickable(Option_7ParticipantsBtn));
            Option_7ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption8ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(Option_8ParticipantsBtn));
            wait.until(ExpectedConditions.elementToBeClickable(Option_8ParticipantsBtn));
            Option_8ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption9ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(Option_9ParticipantsBtn));
            wait.until(ExpectedConditions.elementToBeClickable(Option_9ParticipantsBtn));
            Option_9ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption10ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(Option_10ParticipantsBtn));
            wait.until(ExpectedConditions.elementToBeClickable(Option_10ParticipantsBtn));
            Option_10ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    /**
     *  Methods of POM class: STEP 2: Duration/Price buttons
     */

    public void clickSelectDurationPriceBtn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(SelectDurationPriceBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Select Duration Price button clicked");
    }

    public void clickOption45MinBtn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(Option_45MinBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Option 45 min button clicked");
    }

    public void clickOption90MinBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(Option_90MinBtn));
            wait.until(ExpectedConditions.elementToBeClickable(Option_90MinBtn));
            Option_90MinBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickContinueBtn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(ContinueBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Continue button clicked");
    }

    /**
     *  Methods of POM class: STEP 3 Time Slot Selection buttons (20:00 & 20:45) are updated based on availability and scenario executed
     */
    public void clickIncrementCalendarBtn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(IncrementCalendarBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Increment Calendar button > clicked");
    }

    public String checkDayOfReservation() throws InterruptedException {
        Thread.sleep(1000);
        WebElement dayButton = wait.until(ExpectedConditions.visibilityOf(Day__Btn));
        return dayButton.getText();
    }

    public void clickTimeSlot20_00_4_Btn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(TimeSlot20_00_Btn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Time Slot 20:00 button clicked");
    }

    public void clickTimeSlot20_45_5_Btn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(TimeSlot20_45_Btn));
            wait.until(ExpectedConditions.elementToBeClickable(TimeSlot20_45_Btn));
            TimeSlot20_45_Btn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickConfirmBtn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(ConfirmBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Confirm button clicked");
    }

    public void clickContinueWithoutOptionBtn() throws InterruptedException {
        Thread.sleep(1000);
        WebElement button = wait.until(ExpectedConditions.visibilityOf(ContinueWithoutOptionBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Continue without option button clicked");
    }
}
