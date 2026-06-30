package com.qa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = about:blank
public class WebDevisEntreprise {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public WebDevisEntreprise(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        PageFactory.initElements(driver, this);
    }

    /**
     * Locator of button
     */
    @FindBy(xpath = "//a[@href='/contact-us/company']//div")
    private WebElement OrderDevisEntrepriseButton;

    /**
     * Locator of Form
     */
    @FindBy(xpath = "//input[@name='1']")
    private WebElement LastNameInput;

    @FindBy(xpath = "//input[@name='2']")
    private WebElement FirstNameInput;

    @FindBy(xpath = "//input[@name='3']")
    private WebElement TelephoneInput;

    @FindBy(xpath = "//input[@name='4']")
    private WebElement EmailInput;

    @FindBy(xpath = "//select[@name='13']")
    private WebElement NbrParticipantsSelect;

    // Hours/Minutes Pickers - Target by finding the container that holds 'Heure de début' and locating its clock button
    @FindBy(xpath = "//p[text()='Heure de début']/following::button[contains(@aria-label, 'Choose time')][1]")
    private WebElement DateBtn1;

    @FindBy(xpath = "//p[text()='Heure de fin']/following::button[contains(@aria-label, 'Choose time')][1]")
    private WebElement DateBtn2;

    // Date Picker
    @FindBy(xpath = "(//button[@aria-label='Choose date'])[1]")
    private WebElement DateBtn3;

    @FindBy(xpath = "(//button[@title='Next month'])[1]")
    private WebElement NextMonthBtn;

    /**
     * Methods
     */


    public void clickOnOrderDevisEntrepriseButton() {
        WebElement button = wait.until(ExpectedConditions.visibilityOf(OrderDevisEntrepriseButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Order Devis Entreprise button clicked");
    }

    public void FillLastName(String lastName) throws InterruptedException {
        Thread.sleep(2000);
        WebElement input = wait.until(ExpectedConditions.visibilityOf(LastNameInput));
        input.sendKeys(lastName);
        System.out.println("Last name entered");
    }

    public void FillFirstName(String firstName) throws InterruptedException {
        Thread.sleep(2000);
        WebElement input = wait.until(ExpectedConditions.visibilityOf(FirstNameInput));
        input.sendKeys(firstName);
        System.out.println("First name entered");
    }

    public void FillTelephone(String telephone) throws InterruptedException {
        Thread.sleep(2000);
        WebElement input = wait.until(ExpectedConditions.visibilityOf(TelephoneInput));
        input.sendKeys(telephone);
        System.out.println("Telephone entered");
    }

    public void FillEmail(String email) throws InterruptedException {
        Thread.sleep(2000);
        WebElement input = wait.until(ExpectedConditions.visibilityOf(EmailInput));
        input.sendKeys(email);
        System.out.println("Email entered");
    }

    public void SelectNbrParticipants() throws InterruptedException {
        Thread.sleep(2000);
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOf(NbrParticipantsSelect));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectElement);
        Thread.sleep(2000);
        Select select = new Select(selectElement);
        select.selectByValue(String.valueOf(4));
        System.out.println("Number of participants selected");
    }

    /*
    * The stack trace shows a StaleElementReferenceException on selectHour10 inside the FillFinishDate() method.
    * As stated in the Selenium documentation, an element goes stale when the DOM dynamically changes.
    * In your code, when the time picker menu closes and reopens, the entire dropdown HTML container
    * (MuiMultiSectionDigitalClock-root) is completely destroyed and recreated.
    * Because Page Factory caches elements by default, selectHour10 still points to the old,
    * destroyed menu version, resulting in a crash.To completely solve this,
    * you need to stop using pre-cached @FindBy elements for the dropdown values.
    * Instead, look them up dynamically on-the-fly right after you open the picker.
    * The Solution Remove the dynamic time selectors (selectHour10 and selectMinute50)
    * from your global @FindBy definitions, and write your methods using dynamic runtime locators:
    * */
    // Keep these global locators since they remain stable on the main page
    public void FillStartDate() throws InterruptedException {
        // 1. Open picker
        wait.until(ExpectedConditions.elementToBeClickable(DateBtn1)).click();

        // 2. Locate and click Hour dynamically to prevent Stale exceptions
        By hourLocator = By.xpath("//div[contains(@class, 'MuiMultiSectionDigitalClock-root')]//ul[1]/li[text()='04']");
        wait.until(ExpectedConditions.elementToBeClickable(hourLocator)).click();

        Thread.sleep(600); // Allow DOM container closure animation

        // 3. Re-open picker
        wait.until(ExpectedConditions.elementToBeClickable(DateBtn1)).click();

        // 4. Locate and click Minute dynamically
        By minuteLocator = By.xpath("//div[contains(@class, 'MuiMultiSectionDigitalClock-root')]//ul[2]/li[text()='10']");
        wait.until(ExpectedConditions.elementToBeClickable(minuteLocator)).click();
    }

    public void FillFinishDate() throws InterruptedException {
        // 1. Open picker for Finish Date
        wait.until(ExpectedConditions.elementToBeClickable(DateBtn2)).click();

        // 2. Locate and click Hour dynamically (re-finds the fresh elements in the new DOM structure)
        By hourLocator = By.xpath("//div[contains(@class, 'MuiMultiSectionDigitalClock-root')]//ul[1]/li[text()='04']");
        wait.until(ExpectedConditions.elementToBeClickable(hourLocator)).click();

        Thread.sleep(600);

        // 3. Re-open picker for Finish Date
        wait.until(ExpectedConditions.elementToBeClickable(DateBtn2)).click();

        // 4. Locate and click Minute dynamically
        By minuteLocator = By.xpath("//div[contains(@class, 'MuiMultiSectionDigitalClock-root')]//ul[2]/li[text()='10']");
        wait.until(ExpectedConditions.elementToBeClickable(minuteLocator)).click();
    }

    public void FillDateEvent() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(DateBtn3));
        button.click();
        System.out.println("Date event entered");
    }

    public void ClickNextMonth() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(NextMonthBtn));
        button.click();
        System.out.println("Next month button clicked");
    }

    public void SelectDateOption(String date) throws InterruptedException {
        Thread.sleep(2000);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[normalize-space()='" + date + "'])[1]")));
        option.click();
        System.out.println("Date " + date + " is selected");
    }
}
