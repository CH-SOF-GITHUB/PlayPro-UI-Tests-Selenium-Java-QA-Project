package org.qa.pages.orangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

public class HomePage {


    private ActionDriver actionDriver;

    // Define locators by class
    private By adminTab = By.cssSelector("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > aside:nth-child(1) > nav:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(2)");
    private By userIDBtn = By.className("oxd-userdropdown-name");
    private By logoutBtn = By.linkText("Logout");
    private By orangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']//img");

    // Implementing Page Class for UI Verification compare to DataBase
    private By pimTab = By.xpath("//span[text()='PIM']");
    private By empSearchField = By.xpath("//input[@placeholder='Type for hints...']");
    private By searchBtn = By.xpath("//button[normalize-space()='Search']");
    private By empFirstAndMiddleName = By.xpath("//div[@class='oxd-table-card']/div/div[3]");
    private By empLastName = By.xpath("//div[@class='oxd-table-card']/div/div[4]");

    // Initialize action driver by constructor // Commited for singleton design pattern
    /*public HomePage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }*/
    public HomePage(WebDriver driver) {
        this.actionDriver = BaseClass.getActionDriver();
    }

    // Method to click on PIM Tab
    public void clickOnPIMTab() {
        actionDriver.click(pimTab);
    }

    // Employee Search
    public void searchOfEmployee(String value) {
        actionDriver.enter(empSearchField, value);
        // actionDriver.enter(empSearchField, String.valueOf(Keys.ENTER));
        actionDriver.click(searchBtn);
        actionDriver.scrollToElement(empFirstAndMiddleName);
    }

    // Verify Employee First & Middle Names
    public boolean verifyEmpFirstAndMiddleName(String firstMiddleNameForDB) {
        return actionDriver.compareText(empFirstAndMiddleName, firstMiddleNameForDB);
    }

    // Verify Employee LastName
    public boolean verifyEmpLastName(String lastNameForDB) {
        return actionDriver.compareText(empLastName, lastNameForDB);
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
