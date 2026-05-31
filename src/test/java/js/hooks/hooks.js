import { After, Before } from "@cucumber/cucumber";
import "chromedriver";
import { Builder } from "selenium-webdriver";

Before({ tags: "@login" }, async function () {
  console.log("===========Avant chaque Test de Login===========");
  this.driver = await new Builder().forBrowser("chrome").build(); // Initialize the driver here and store it in 'this.driver'
  await this.driver.manage().window().maximize();
});

After({ tags: "@login" }, async function () {
  console.log("===========Après chaque Test de Login===========");
  await this.driver.quit(); // Close the driver after the test
});

Before({ tags: "@maths" }, async function () {
  // No WebDriver needed for these tests
  console.log("===========Avant chaque Test de Maths===========");
  // You could do some other setup here for tests that don't need a WebDriver
});

After({ tags: "@maths" }, async function () {
  // No WebDriver cleanup needed here
  console.log("===========Après chaque Test de Maths===========");
});
