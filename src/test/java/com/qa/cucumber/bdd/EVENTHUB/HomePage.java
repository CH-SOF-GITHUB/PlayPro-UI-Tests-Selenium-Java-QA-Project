package com.qa.cucumber.bdd.EVENTHUB;

import com.qa.cucumber.bdd.Hooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;

public class HomePage {

    // Add Constructor of class page with Singleton Design Pattern
    private ActionDriver actionDriver;

    // Add locators for the login page elements
    private final By TitleHomePage = By.xpath("//h1[@class='text-4xl sm:text-5xl lg:text-6xl font-extrabold tracking-tight mb-6 leading-tight']");
    private final By SubTitleHomePage = By.xpath("//p[@class='text-indigo-100 text-lg sm:text-xl max-w-2xl mx-auto mb-10 leading-relaxed']");
    private final By BrowseEventsBtn = By.xpath("//span[@class='inline-flex items-center justify-center px-6 py-2.5 text-base font-semibold rounded-lg bg-white text-indigo-700 hover:bg-indigo-50 transition-colors w-full sm:w-auto']");
    private final By MyBookingsBtn = By.xpath("//button[normalize-space()='My Bookings']");

    private final By FeaturedEventsSectionTitle = By.xpath("//h2[normalize-space()='Featured Events']");


    // Add Constructor of class page with Singleton Design Pattern
    public HomePage(WebDriver driver) {
        this.actionDriver = Hooks.getActionDriver();
    }

    // Methods
    public void checkHomePageTitleByText(String expectedTitle) {
        String actualTitle = actionDriver.getText(TitleHomePage);
        boolean isTitleCorrect = actualTitle.contentEquals(expectedTitle);
        org.testng.Assert.assertTrue(isTitleCorrect, "Expected title: " + expectedTitle + ", but got: " + actualTitle);
    }

    public void checkHomePageSubTitleByText(String expectedSubTitle) {
        String actualSubTitle = actionDriver.getText(SubTitleHomePage);
        boolean isSubTitleCorrect = actualSubTitle.equals(expectedSubTitle);
        org.testng.Assert.assertTrue(isSubTitleCorrect, "Expected subtitle: " + expectedSubTitle + ", but got: " + actualSubTitle);
    }

    public void checkFeaturedEventsSectionTitle(String expectedSectionTitle) {
        String actualSectionTitle = actionDriver.getText(FeaturedEventsSectionTitle);
        boolean isSubTitleCorrect = actualSectionTitle.equals(expectedSectionTitle);
        org.testng.Assert.assertTrue(isSubTitleCorrect, "Expected subtitle: " + expectedSectionTitle + ", but got: " + actualSectionTitle);
    }

    public void clickOnMyBookingsButton() {
        actionDriver.click(MyBookingsBtn);
    }
}
