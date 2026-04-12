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
    // java
    @FindBy(xpath = "(//*[name()='svg'])[8]")
    private WebElement IncrementCalendarBtn;

    @FindBy(xpath = "(//p[contains(@class,'cursor-pointer text-center font-poppins lg:text-base leading-4 tracking-[0%] font-normal text-sm outline-none rounded-lg text-black')])[1]")
    private WebElement Day_14_04_2026_Btn;

    @FindBy(css = "div[class='grid-cols-[repeat(auto-fit,minmax(80px,1fr))] md:grid-cols-[repeat(auto-fit,minmax(80px,80px))] lg:gap-2 grid gap-y-3 justify-center gap-x-1'] div:nth-child(1) button:nth-child(1)")
    private WebElement TimeSlot20_45_5_Btn;

    @FindBy(xpath = "(//button[@type='button'])[6]")
    private WebElement TimeSlot20_45_6_Btn;

    @FindBy(xpath = "(//button[@type='button'])[5]")
    private WebElement TimeSlot20_00_5_Btn;

    @FindBy(css = "div[class='grid-cols-[repeat(auto-fit,minmax(80px,1fr))] md:grid-cols-[repeat(auto-fit,minmax(80px,80px))] lg:gap-2 grid gap-y-3 justify-center gap-x-1'] div:nth-child(1) button:nth-child(1)")
    private WebElement TimeSlot20_00_4_Btn;

    @FindBy(xpath = "(//button[@class='opacity-100 w-full lg:w-1/3 h-[60px] rounded-md px-[15px] py-[7px] gap-[10px] opacity-100 font-poppins font-medium text-[15px] leading-[21px] tracking-[0px] text-center bg-primary text-accent'])[1]")
    private WebElement ConfirmBtn;

    @FindBy(xpath = "(//button[normalize-space()='Continuer sans option'])[1]")
    private WebElement ContinueWithoutOptionBtn;

    /**
     *  Method of POM class: Experience Card Path
     */

    public void clickVrPartyTestCard() throws InterruptedException {
        try {
            // wait for 3s
            Thread.sleep(3000);
            // scroll down to the experience card
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Scroll down to the experience card
            for (int i = 0; i < 3; i++) {
                js.executeScript("window.scrollBy(0, 300);");
                Thread.sleep(1000);
            }
            wait.until(ExpectedConditions.elementToBeClickable(Vr_Party_Test));
            Vr_Party_Test.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    /**
     *  Methods of POM class: STEP 1 Reservation Selection buttons
     */
    public void clickSelectParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(SelectParticipantsBtn));
            SelectParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption4ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(Option_4ParticipantsBtn));
            Option_4ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption5ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(Option_5ParticipantsBtn));
            Option_5ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption6ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(Option_6ParticipantsBtn));
            Option_6ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption7ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(Option_7ParticipantsBtn));
            Option_7ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption8ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(Option_8ParticipantsBtn));
            Option_8ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption9ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(Option_9ParticipantsBtn));
            Option_9ParticipantsBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption10ParticipantsBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
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
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(SelectDurationPriceBtn));
            SelectDurationPriceBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption45MinBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(Option_45MinBtn));
            Option_45MinBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickOption90MinBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(Option_90MinBtn));
            Option_90MinBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickContinueBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(ContinueBtn));
            ContinueBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    /**
     *  Methods of POM class: STEP 3 Time Slot Selection buttons (20:00 & 20:45) are updated based on availability and scenario executed
     */
    public void clickIncrementCalendarBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(IncrementCalendarBtn));
            IncrementCalendarBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public String checkDay14_04_2026_Btn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(Day_14_04_2026_Btn));
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return Day_14_04_2026_Btn.getText();
    }

    public void clickTimeSlot20_00_5_Btn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(TimeSlot20_00_5_Btn));
            TimeSlot20_00_5_Btn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickTimeSlot20_00_4_Btn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(TimeSlot20_00_4_Btn));
            TimeSlot20_00_4_Btn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }


    public void clickTimeSlot20_45_5_Btn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(TimeSlot20_45_5_Btn));
            TimeSlot20_45_5_Btn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickTimeSlot20_45_6_Btn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(TimeSlot20_45_6_Btn));
            TimeSlot20_45_6_Btn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickConfirmBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(ConfirmBtn));
            ConfirmBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void clickContinueWithoutOptionBtn() throws InterruptedException {
        try {
            Thread.sleep(1000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 300);");
            wait.until(ExpectedConditions.elementToBeClickable(ContinueWithoutOptionBtn));
            ContinueWithoutOptionBtn.click();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
