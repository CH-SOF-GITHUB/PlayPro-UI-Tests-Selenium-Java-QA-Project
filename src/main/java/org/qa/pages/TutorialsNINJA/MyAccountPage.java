package org.qa.pages.TutorialsNINJA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.qa.actionDriver.ActionDriver;
import org.qa.base.BaseClass;

import java.util.ArrayList;
import java.util.List;

public class MyAccountPage {
    private ActionDriver actionDriver;


    // Locate Web Element of Success Message
    private final By MyAccountTitle = By.xpath("//div[@id='content']/h2");

    // Locate Web(s) Element(s) to change password
    private final By PasswordLink = By.linkText("Password");
    private final By NewPwdField = By.id("input-password");
    private final By ConfirmNewPwdField = By.id("input-confirm");
    private final By ContinueBtn = By.xpath("//input[@value='Continue']");

    private final By ContentHeadings = By.xpath("//div[@id='content']//h2");

    // Add a constructor of class page with Singleton Design Patter
    public MyAccountPage(WebDriver driver) {
        this.actionDriver = BaseClass.getActionDriver();
    }

    // Check of a success message displays when login pass
    public boolean compareMyAccountTitle(String ExpectedSuccessMsg) {
        return actionDriver.compareText(MyAccountTitle, ExpectedSuccessMsg);
    }

    // Methods to change with new password
    public void passToNewPwd() {
        actionDriver.click(PasswordLink);
    }

    public void enterNewPwd(String pwd) {
        actionDriver.enter(NewPwdField, pwd);
        actionDriver.enter(ConfirmNewPwdField, pwd);
        actionDriver.click(ContinueBtn);
    }

    public List<String> getContentHeadings() {
        List<WebElement> listOfContents = actionDriver.getElements(ContentHeadings);
        List<String> Texts = new ArrayList<>();
        for (WebElement element : listOfContents) {
            Texts.add(element.getText());
        }
        return Texts;
    }

}
