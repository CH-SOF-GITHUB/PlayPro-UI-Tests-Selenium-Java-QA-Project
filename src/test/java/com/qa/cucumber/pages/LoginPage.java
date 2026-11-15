package com.qa.cucumber.pages;
import com.qa.cucumber.Utility.Base;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Base {

    // Locators
    @FindBy(className = "login_logo")
    private WebElement loginContainerLogo;

    @FindBy(id="user-name")
    private WebElement txtUserName;

    @FindBy(id="password")
    private WebElement textPassword;

    @FindBy(id="login-button")
    private WebElement btnLogin;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMsg;

    // Constructor
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    // Getter and Setter Methods //
    public WebElement getLoginContainerLogo() {
        return loginContainerLogo;
    }

    public void setLoginContainerLogo(WebElement loginContainerLogo) {
        this.loginContainerLogo = loginContainerLogo;
    }

    public WebElement getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(WebElement txtUserName) {
        this.txtUserName = txtUserName;
    }

    public WebElement getTextPassword() {
        return textPassword;
    }

    public void setTextPassword(WebElement textPassword) {
        this.textPassword = textPassword;
    }

    public WebElement getBtnLogin() {
        return btnLogin;
    }

    public void setBtnLogin(WebElement btnLogin) {
        this.btnLogin = btnLogin;
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(WebElement errorMsg) {
        this.errorMsg = errorMsg;
    }
}
