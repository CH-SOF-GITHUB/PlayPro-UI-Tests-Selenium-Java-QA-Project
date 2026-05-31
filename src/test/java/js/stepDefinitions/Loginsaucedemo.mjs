import { Given, Then, When } from "@cucumber/cucumber";
import { By } from "selenium-webdriver"; // Ensure you import By from selenium-webdriver
import assert from "assert";
import "chromedriver";

Given("I am on the login page", async function () {
  // Access the driver from 'this'
  await this.driver.get("https://www.saucedemo.com/");
  console.log("I am on the login page passed");
});

When("I login with a valid credentials", async function () {
  await this.driver.findElement(By.id("user-name")).sendKeys("standard_user");
  await this.driver.findElement(By.id("password")).sendKeys("secret_sauce");
  await this.driver.findElement(By.id("login-button")).click();
  console.log("I login with a valid credentials passed");
});

When("I am logged in", async function () {
  assert.strictEqual(await this.driver.getTitle(), "Swag Labs");
  console.log("I am logged in passed");
});

Then("I am taken into the products page", async function () {
  const titleproduct = await this.driver
    .findElement(By.className("title"))
    .getText();
  console.log("Title: " + titleproduct);
  assert.strictEqual(titleproduct, "Products");
  console.log("I am taken into the products page passed \n\n");
});