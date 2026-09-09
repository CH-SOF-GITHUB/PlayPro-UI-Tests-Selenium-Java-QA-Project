package org.qa.pages.orangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;

public class HomePage {


    private ActionDriver actionDriver;

    // Define locators by class
    private By adminTab = By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > aside:nth-child(1) > nav:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(2)");
    private By userIDBtn = By.className("oxd-userdropdown-name");
    private By logoutBtn = By.linkText("Logout");
    private By orangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']//img");

    // Initialize action driver by constructor
    public HomePage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }

    // Method to verify admin tab is visible
    public boolean isAdminTabVisible() {
        return actionDriver.isDisplayed(adminTab);
    }

    public boolean verifyOrangeHRMLogo() {
        return actionDriver.isDisplayed(orangeHRMLogo);
    }

    // Method to perform logout operation
    public void logout() {
        actionDriver.click(userIDBtn);
        actionDriver.click(logoutBtn);
    }

}
