package org.qa.pages.TutorialsNINJA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

public class RightColumnPage {

    private ActionDriver actionDriver;

    public RightColumnPage(WebDriver driver) {
        this.actionDriver = BaseClass.getActionDriver();
    }

    // Locate Some Web Elements in the page
    private final By LoginRightColumn = By.xpath("//aside[@id=\"column-right\"]//div[@class=\"list-group\"]//a[text()='Login']");
    private final By NewsLetterRightColumn = By.xpath("//aside[@id=\"column-right\"]//div[@class=\"list-group\"]//a[text()='Newsletter']");
    private final By RegisterRightColumn = By.xpath("//aside[@id=\"column-right\"]//div[@class=\"list-group\"]//a[text()='Register']");

    // Methods
    public void clickLoginRightColumn() {
        actionDriver.click(LoginRightColumn);
    }

    public void clickNewsLetterRightColumn() { actionDriver.click(NewsLetterRightColumn); }

    public void clickRegisterRightColumn() { actionDriver.click(RegisterRightColumn); }
}
