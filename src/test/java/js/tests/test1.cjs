import 'chromedriver';
import { Builder, By, Key, until } from 'selenium-webdriver';
import assert, { equal } from 'assert';

// definir web driver: controle et ouverture un navigateur chrome
const driver = await new Builder().forBrowser('chrome').build();

// Test case 1: Search for a specific term
await driver.get('https://www.bstackdemo.com/');
// maximiser la fenêtre
await driver.manage().window().maximize();
// vérifier si le titre de la page est correct
const title = await driver.getTitle();
assert.strictEqual(title, 'StackDemo');
setTimeout(() => {
    console.log("waiting for 5 seconds");
}, 5000);
// saisir un terme de recherche
driver.close();
console.log("test passed");