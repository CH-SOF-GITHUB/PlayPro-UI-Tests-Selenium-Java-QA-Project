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
5) Run Tests in Headless Mode:  `npx cypress run`
6) Run Cypress Test following the Correct Browser: `npx cypress run -- --browser chrome`
7) Run a specific cypress Test: `npx cypress run --browser chrome --spec '<path to test file>'`
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
`npx mochawesome-merge cypress/results/mochawesome_003.json -o mochawesome.json`
`npx mochawesome-merge cypress/results/*.json -o mochawesome-report/mochawesome.json`
STEP 4: Générer le rapport HTML autonome avec marge (mochawesome-report-generator) :
À partir du JSON fusionné (mochawesome.json), la commande marge produit le rapport HTML final : 
`npx marge mochawesome.json`
`npx marge mochawesome-report/mochawesome.json -o mochawesome-report`
Cela crée le rapport HTML complet dans mochawesome-report/mochawesome.html, incluant les résultats des tests, les temps d'exécution et le code/corps des tests.

### Cypress AI Agents
Rôle des 5 Cypress AI Skills:
cypress-author    : Crée, met à jour et corrige les tests E2E et Component 
                    En s'adaptant à ta configuration, ta version de Cypress, tes fixtures et tes commandes personnalisées.   
cypress-explain   : Audite, résume ou explique des tests existants et signale les sélecteurs fragiles, attentes inutiles ou assertions manquantes.   
cypress-tap       : Exécute et débogue un test contre une session cypress open active (Chromium) et inspecte Command Log, erreurs et snapshots DOM.   
cypress-cloud-cli : Diagnostique les échecs et tests flaky enregistrés sur Cypress Cloud via Test Replay.   
cypress-docs      : Interroge la documentation officielle docs.cypress.io (notamment /llms.txt) selon ta version de Cypress.   

Step 1: Installer Cypress AI Skills: `npx skills add cypress-io/ai-toolkit`
Step 2: Ouvrir Ton agent Codex sur le dossier cypressFramework
Step 3: Rédiger Prompt de Test en utilisant `/cypress-author` agent skill
Step 4: Exécuter nouvel Test Crée
        `npx cypress open`
         Prompt: Utilise `/cypress-tap` en ciblant le test :
         {{/cypress-tap Run swag_labs_cart.cy.js against my open Cypress session, poll for a verdict, and if it fails, 
           read the failing test's Command Log and error and inspect the app at the failing command to fix it. Use minimal exploration.}}
Step 5: Continuer de tester autres scénarios.

### Cypress Performance Testing ?
Cypress is widely used for E2E and UI Testing, but with the help of plugins and custom utilities, it can also support performance testing.
By extending its functionality, Cypress can monitor page load times, track key performance metrics, and analyze application Behavior 
under various conditions. 

### Load Testing ?
While Cypress is primarily built for functional and UI testing in modern JavaScript applications, 
it can be extended to support load testing through community-driven plugins.

Although Cypress doesn’t natively support performance testing, tools like Google Lighthouse can be integrated 
to simulate user load and assess app performance under stress.

This allows teams to conduct basic load and stress testing using Cypress as part of a broader performance testing strategy.

### Performance Testing With Cypress And Google Lighthouse
Google Lighthouse is a powerful tool integrated into Chrome that measures web performance and identifies issues such as :
slow loading times, inefficient images, and poorly optimized code.

         +------------------------------------------------------------------------------+
         |                   CHOIX D'UNE APPROCHE DE PERFORMANCE                        |
         +------------------------------------------------------------------------------+
                                                   |
                     +-----------------------------+-----------------------------+
                     |                                                           |
                     v                                                           v
         +----------------------------------------+                +---------------------------+
         |     COTE NAVIGATEUR (FRONT-END)        |                |  CHARGE SERVEUR / API     |
         |        Avec Cypress (v16 / ESM)        |                |  (Hors périmètre Cypress)|
         +----------------------------------------+                +---------------------------+
                               |                                                  |
         +---------------------+------------------------+                         v
         |                                              |                +-------------------+
         v                                              v                | k6 / JMeter /     |
         +---------------------------------+  +-----------------------+  |   Gatling         |
         |  @cypress-audit/lighthouse      |  | API window.performance|  +-------------------+
         +---------------------------------+  +-----------------------+  | Cas: 100+ users,  |
         | Cas: Core Web Vitals (LCP, CLS, |  | Cas: Budgets temps    |  | stress & charge   |
         |      TBT), SEO, Accessibilité   |  |      parcours, TTFB   |  | serveur           |
         | Dep: Package npm + config.js    |  | Dep: Aucune (natif JS)|  | Dep: Outil externe|
         | Nav: Chromium uniquement        |  | Nav: Tous navigateurs |  | Nav: N/A (réseau) |
         +---------------------------------+  +-----------------------+  +-------------------+

### Different ways to do Cross Browser Testing With Cypress
By performing cross browser testing with Cypress, testers can identify any issues or inconsistencies before the 
website or the application is released to the public.
- using Cypress test runner:
  1): open cypress 
  2): select the type of testing: E2E or Component
  3): choose the browser of your choice 
  4): you can also change the browser from the Spec Runner screen by Menu

- using cypress test file:
  it("test case name", { browser: "chrome" }, () => {})

- using browser configuration in cypress CLI:
  Cypress runs in Electron by default in both headed and headless mode.
  run your test case on the specified browser in headless mode: `npx cypress run --browser <browsername>`
  run your test case on the specified browser in headed mode:   `npx cypress open --browser firefox`
  `npx cypress run --browser chrome --spec "cypress/e2e/example_cross_browser_test_file_spec.cy.js"`
  `npx cypress run --browser firefox --spec "cypress/e2e/example_cross_browser_test_file_spec.cy.js"`
  `npx cypress run --browser edge --spec "cypress/e2e/example_cross_browser_test_file_spec.cy.js"`
- using package.json:
  "scripts": {
     "test-browser-chrome": "npx cypress run --browser chrome"
     "test-browser-firefox": "npx cypress run --browser firefox",
     "test-browser-edge": "npx cypress run --browser edge"
  }
  npm run test-browser-chrome
- using cloud platform
- using CI/CD
- using Docker on CI/CD

### Run Safari Tests in Cypress
1) Install the WebKit engine as a dev dependency: `npm install playwright-webkit --save-dev` 
2) Install the WebKit browser: `npx playwright install webkit`
3) Enable it in your Cypress config with `experimentalWebKitSupport: true`
4) Run the suite against WebKit: `npx cypress run --browser webkit --spec "cypress/e2e/example_test_spec.cy.js"`

### Execute your Tests:
npx cypress run --browser chrome --spec "cypress/e2e/SwagLabs/login_valid.cy.js"
