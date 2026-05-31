import { Given, When, Then } from "@cucumber/cucumber";
import assert from "assert";

Given("I have entered a first number {int}", function (int) {
  console.log("Type a first number {int}", int);
  this.number1 = int;
});

Given("I have entered a second number {int}", function (int) {
  console.log("Adding a second number {int}", int);
  this.number2 = int;
});

When("I choose to add the numbers together", function () {
  // Write code here that turns the phrase above into concrete actions
  this.total = this.number1 + this.number2;
  console.log("The total is: " + this.total);
});

Then("the result should be the sum of the numbers {int}", function (int) {
  // Write code here that turns the phrase above into concrete actions
  console.log("The result should be the sum of the numbers");
  assert.strictEqual(this.total, int);
  //assert(this.total === int + "Expected: " + int + " . But got: " + this.total);
  //assert.ok(this.total === 16);
});
