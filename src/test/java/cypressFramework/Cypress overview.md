Cypress is an open-source testing framework based on JavaScript that supports web application testing. 
Unlike Selenium, It works completely on a real browser without the need for driver binaries. 
The automated code and application code share the same platform, which gives complete control over the application under test.
Cypress lets you write test scripts using JavaScript or TypeScript. 
It comes with Mocha (a popular JavaScript testing framework) built-in, so you don’t need to install Mocha separately.

Cypress automation testing runs on NodeJS server, which communicates with the test runner(Browser) instrumentalized 
by Cypress to run the application (one of the iframe) and test code(another iframe) in the same event loop.

This in turn allows the Cypress automation testing code to mock and even change the JavaScript object on the fly. 
This is one of the primary reasons why Cypress automation testing is expected to execute faster than corresponding Selenium test automation.

Keys benefits of using Cypress test automation framework:
- Cypress is easy to set up.
- Cypress has an in-built wait for requests feature that eliminates the need to configure additional waits.
- There is no need for driver binaries in Cypress automation testing and execution happens on the real browser.
- Since test code and application run in the same browser, it can access application JS objects.
- Cypress automation testing lets you change code and execute the same on the fly.
- It provides dashboard support for detailed reporting.
- It supports parallel test execution.
- It has support for BDD and TDD style testing.
- Cypress tests are less flaky due to the auto-wait features supported by the framework.
- At each step, screen grabs that show the test behavior are captured. These can be super helpful in debugging issues with the tests.



Your Agent Writes Better Tests When It Can See Your App.
Left to source code alone, your agent guesses at what your app does. 
`Cypress Tap` gives it a live cypress open session to author from: 
what actually rendered in the DOM, the accessibility tree, and your app's real state at any moment in a run. 
Now it writes selectors and assertions from how your app truly behaves, and checks its own work. No guessing. 
No pasting HTML back and forth.

Now available in Beta. Requires Cypress 15.21.0+.
How Cypress Tap works: https://www.cypress.io/blog/cypress-tap-cli?utm_medium=Cloud+Message+Banner&utm_campaign=cypress-tap-cli&utm_source=Binary%3A+App&utm_medium=Cloud+Message+Banner&utm_campaign=cypress_tap_cli&utm_source=Binary%3A+App
Get the skill: https://docs.cypress.io/app/tooling/ai-skills?utm_medium=Cloud+Message+Banner&utm_campaign=cypress-tap-cli&utm_source=Binary%3A+App%23cypress-tap&utm_medium=Cloud+Message+Banner&utm_campaign=cypress_tap_cli&utm_source=Binary%3A+App
LambdaTest: https://www.testmuai.com/blog/cypress-test-automation-framework/
Cypress BDD: https://www.testmuai.com/learning-hub/cypress-cucumber-tutorial

### Cypress installation du Project:
1) Create the package.json file: `npm init`
2) Install Cypress using the below command: `npm install cypress --save-dev`
3) Install Cypress binary:
   `npm install-scripts approve cypress
    npx cypress install
    npx cypress verify`
4) Launching Cypress in Headed Mode (Mode is visible browser window, view each step and it executes in real time):
   `npx cypress open`
5) Run Tests in Headless Mode: `npx cypress run`
6) Run Cypress Test following the Correct Browser: `npx cypress run -- --browser chrome`
7) Run a specific cypress Test: `npx cypress run -- --browser chrome --spec '<path to test file>'`
8) Run Cloud Cypress io Tests:  `npx cypress run --record --key 97487063-2e65-4977-bce4-3f0364edbae6`

### How to Run Cypress Tests With TestMu AI?
Step 1: Get your TestMu AI Username and an Access Key from your TestMu AI Account Settings > Password & Security.
Step 2: Install the TestMu AI Cypress CLI using the below command: `npm install -g lambdatest-cypress-cli`
Step 3: In your project root directory, generate a sample config file by running the below command: `lambdatest-cypress init`
Step 4: Update the generated lambdatest-config.json file with your details.
Step 5: Once your configuration file is ready, run the tests using the below command: `lambdatest-cypress run --sync=true --cy="--config-file cypress.config.js"`
Step 6: Visit the TestMu AI Web Automation dashboard to view your Cypress test results.

### Assertions
Mocha itself doesn’t include built-in assertion libraries. 
Cypress, however, provides its assertion library and extends Mocha with Chai assertions. 
Cypress uses Chai assertions to assert conditions in your tests.
Some of the Examples are .should('have.text', 'Expected Text'),
                         .should('not.equal', 'Expected Text'),
                         .should('exist'),
                         .should('not.be.disabled'), 
                         .should('have.class', 'active'), etc.
### Exclude Tests
use the .skip() method to exclude a specific Cypress test.

### Include Tests
use the .only() method to execute only the test related.

### Test Suite
use Describe Block describe("", () => {...})

### How use AI to write Cypress Tests?
cy.prompt is an AI-powered Cypress command that turns plain-English test steps into executable Cypress commands.


### Pipeline de génération de rapport en 3 étapes (documentation officiel Cypress)
STEP 1: Install Mochawesome:
`npm install --save-dev mochawesome mochawesome-merge mochawesome-report-generator`
STEP 2: Execute Cypress to generate only files JSON :
La configuration Cypress doit désactiver la génération HTML (html: false) et activer l'export JSON (json: true) dans le dossier cypress/results.
En ligne de commande, l'équivalent direct est : `npx cypress run`
Chaque exécution ou spec produit un fichier JSON distinct dans cypress/results/ 
(par exemple cypress/results/mochawesome.json, cypress/results/mochawesome_001.json, etc.).
Step 3: Fusionner les fichiers JSON avec mochawesome-merge :
Une fois les fichiers JSON générés dans cypress/results/, on les regroupe en un seul fichier JSON consolidé :
`npx mochawesome-merge cypress/results/*.json -o mochawesome.json`
STEP 4: Générer le rapport HTML autonome avec marge (mochawesome-report-generator) :
À partir du JSON fusionné (mochawesome.json), la commande marge produit le rapport HTML final : `npx marge mochawesome.json`
Cela crée le rapport HTML complet dans mochawesome-report/mochawesome.html, incluant les résultats des tests, les temps d'exécution et le code/corps des tests.
