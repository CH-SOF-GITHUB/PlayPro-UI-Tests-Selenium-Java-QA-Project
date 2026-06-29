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

    @FindBy(xpath = "(//button[@aria-label='Choose time'])[1]")
    private WebElement DateBtn1;

    @FindBy(xpath = "(//li[@aria-label='12 hours'])[1]")
    private WebElement TwelveHoursOption;

    @FindBy(xpath = "(//input[@placeholder='HH:mm'])[2]/following::button[@aria-label='Choose time'][1]")
    private WebElement DateBtn2;

    @FindBy(xpath = "(//button[@aria-label='Choose date'])[1]")
    private WebElement DateBtn3;

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

    public void FillStartDate() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(DateBtn1));
        button.click();
        System.out.println("Start date entered");
        Actions actions = new Actions(driver);
        for (int i = 0; i < 12; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }
    }

    public void SelectTwelveHoursOption() throws InterruptedException {
        Thread.sleep(2000);
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(TwelveHoursOption));
        option.click();
        System.out.println("12 hours option selected");
    }

    public void FillFinishDate() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(DateBtn2));
        button.click();
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("Finish date entered");
        Actions actions = new Actions(driver);
        for (int i = 0; i < 12; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }
    }

    public void FillDateEvent() throws InterruptedException {
        Thread.sleep(2000);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(DateBtn3));
        button.click();
        System.out.println("Date event entered");
    }
}
