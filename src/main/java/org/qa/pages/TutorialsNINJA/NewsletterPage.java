package org.qa.pages.TutorialsNINJA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

public class NewsletterPage {

    // Default URL: https://tutorialsninja.com/demo/index.php?route=account/newsletter

    private ActionDriver actionDriver;

    public NewsletterPage(WebDriver driver) {
        this.actionDriver = BaseClass.getActionDriver();
    }

    // Locate Web Element(s) depending on NewsLetter
    private final By NewsLetterTitle = By.xpath("//h1[normalize-space()='Newsletter Subscription']");

    private final By SubscribeYES = By.xpath("//input[@value='1']");
    private final By SubscribeNO = By.xpath("//input[@value='0']");

    // Methods
    public boolean isInNewsletter() {
        return actionDriver.isDisplayed(NewsLetterTitle);
    }

    public boolean isNewsLetterYESChecked(String expectedValue) {
        return actionDriver.compareByAttribute(SubscribeYES, "checked", expectedValue);
    }

    public boolean isNewsLetterNOChecked(String expectedValue) {
        return actionDriver.compareByAttribute(SubscribeNO, "checked", expectedValue);
    }

}
