package Mobile.appium.testing;

import Mobile.appium.Base;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class TC01 extends Base {


    @Test(groups = {"smoke"})
    public void SampleValidLogin() throws InterruptedException {
        // TODO: implement test steps for valid login
        // fill email and password fields and submit the form
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.clear();
        emailField.sendKeys("demotenant3@yopmail.com");
        Thread.sleep(2000);
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.click();
        passwordField.sendKeys("Admin1234!");
        Thread.sleep(2000);
        // hide keyboard
        driver.hideKeyboard();
        WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        submitBtn.click();
        // wait to load page
        Thread.sleep(7000);
        // expect the result to be a successful login and redirection to home page
        String expectedUrl = "https://demotenant.playpro.fr/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("After login expected and actual Url are mismatch !!", expectedUrl, actualUrl);
        status = "passed";
    }
}
