package org.qa.pages.EventHUB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    // Define the WebDriver instance variable
    private WebDriver driver;

    // Define the WebDriverWait instance variable
    private WebDriverWait wait;

    // Constructor to initialize the WebDriver
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait time
    }

    // Define locators for elements on the HomePage
    // Add locators for the login page elements
    private final By TitleHomePage = By.xpath("//h1[@class='text-4xl sm:text-5xl lg:text-6xl font-extrabold tracking-tight mb-6 leading-tight']");
    private final By SubTitleHomePage = By.xpath("//p[@class='text-indigo-100 text-lg sm:text-xl max-w-2xl mx-auto mb-10 leading-relaxed']");
    private final By BrowseEventsBtn = By.xpath("//span[@class='inline-flex items-center justify-center px-6 py-2.5 text-base font-semibold rounded-lg bg-white text-indigo-700 hover:bg-indigo-50 transition-colors w-full sm:w-auto']");
    private final By MyBookingsBtn = By.xpath("//button[normalize-space()='My Bookings']");

    private final By FeaturedEventsSectionTitle = By.xpath("//h2[normalize-space()='Featured Events']");

    private final By ViewAllLink = By.xpath("//a[contains(text(),'View all →')]");

    private final By ExploreAllEventsBtn = By.xpath("//button[normalize-space()='Explore All Events']");


    // Add methods to interact with the HomePage elements here
    public boolean checkHomePageTitleByText(String expectedTitle) {
        String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(TitleHomePage)).getText();
        return actualTitle.contentEquals(expectedTitle);
    }

    public boolean checkHomePageSubTitleByText(String expectedSubTitle) {
        String actualSubTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(SubTitleHomePage)).getText();
        return actualSubTitle.equals(expectedSubTitle);
    }

    public boolean checkFeaturedEventsSectionTitle(String expectedSectionTitle) {
        String actualSectionTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(FeaturedEventsSectionTitle)).getText();
        return actualSectionTitle.equals(expectedSectionTitle);
    }

    public void clickOnBrowseEventsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(BrowseEventsBtn)).click();
    }

    public void clickOnMyBookingsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(MyBookingsBtn)).click();
    }

    public void clickOnViewAllLink() {
        wait.until(ExpectedConditions.elementToBeClickable(ViewAllLink)).click();
    }

    public void clickOnExploreAllEventsBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(ExploreAllEventsBtn)).click();
    }
}

