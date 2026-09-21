package org.qa.pages.TutorialsNINJA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

public class FooterPage extends BaseClass {

    private ActionDriver actionDriver;

    public FooterPage() {
        this.actionDriver = new ActionDriver(getDriver());
    }

    // Locate web elements of footer
    private final By aboutUSLink = By.xpath("//ul[@class='list-unstyled']//li//a[text()='About Us']");

    // Methods
    public void clickOnAboutUS() {
        actionDriver.clickUsingJS(aboutUSLink);
    }
}
