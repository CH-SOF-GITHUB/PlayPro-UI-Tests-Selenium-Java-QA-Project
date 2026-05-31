// Add your step definitions here
import { Given, When, Then } from '@cucumber/cucumber';
import assert from "assert";

Given('today is {string}', function (day) {
  this.today = day;
});

When('I ask whether it\'s Friday yet', function () {
  this.actualAnswer = (this.today === 'Friday') ? 'Yes' : 'No';
});

Then('I should be told {string}', function (expectedAnswer) {
  assert.strictEqual(this.actualAnswer, expectedAnswer);
});
