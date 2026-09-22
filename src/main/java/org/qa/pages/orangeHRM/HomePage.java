package org.qa.pages.orangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

public class HomePage {


    private ActionDriver actionDriver;

    // Define locators by class
    private final By adminTab = By.xpath("//span[text()='Admin']");
    private final By userIDBtn = By.className("oxd-userdropdown-name");
    private final By logoutBtn = By.linkText("Logout");
    private final By orangeHRMLogo = By.xpath("//div[@class='oxd-brand-banner']//img");

    // Implementing Page Class for UI Verification compare to DataBase
    private final By pimTab = By.xpath("//span[text()='PIM']");
    private final By empSearchField = By.xpath("//input[@placeholder='Type for hints...']");
    private final By searchBtn = By.xpath("//button[normalize-space()='Search']");
    private final By empFirstAndMiddleName = By.xpath("//div[@class='oxd-table-card']/div/div[3]");
    private final By empLastName = By.xpath("//div[@class='oxd-table-card']/div/div[4]");

    // Initialize action driver by constructor // Commited for singleton design pattern
    /*public RightColumnPage(WebDriver driver) {
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
