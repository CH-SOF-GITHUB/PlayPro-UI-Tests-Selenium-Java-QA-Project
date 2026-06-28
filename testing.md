(Selenium Java Details and Revision)|
-------------------------------------
L'erreur ElementNotInteractableException (en Java) signifie que votre script Selenium a bien repéré l'élément dans le code HTML (DOM), mais ne peut pas interagir avec lui.
Les causes les plus courantes sont :
- Temps de chargement : La page s'exécute trop vite et l'élément n'est pas encore visible ou actif.
- Masquage ou chevauchement : Un autre élément (menu, pop-up) recouvre votre cible.
- Élément caché : L'élément existe mais possède un attribut du type display: none.
- Hors champ (Viewport) : L'élément est présent mais nécessite un défilement (scroll) de la page pour apparaître à l'écran.

Voici les solutions les plus rapides pour y remédier :
1. Utiliser un WebDriverWait (Attente explicite)Ajoutez une attente pour forcer Selenium à patienter jusqu'à ce que l'élément soit cliquable avant d'interagir :javaWebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
   WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("votre-id")));
   element.click();
   Utilisez le code avec précaution.

2. Faire défiler l'écran jusqu'à l'élément (Scroll)Si l'élément est en dehors de la zone visible, utilisez un JavascriptExecutor pour le faire défiler :java
   WebElement element = driver.findElement(By.id("votre-id"));
   ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
   element.click();
   Utilisez le code avec précaution.

3. Cliquer via JavaScript (Alternative)Si l'élément est masqué par le design CSS du site, forcez le clic via le DOM :java
   WebElement element = driver.findElement(By.id("votre-id"));
   ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Allure Report: is a reporting framework, it's opened with differents versions: Allure 2, Allure 3 ...
and it supports many languages and frameworks and can be added to your testing workflow with little to zero configuration.
It products visually rich HTML reports based on your test results that can be easily shared with your team and stakeholders. 
It provides detailed insights into test execution, including test steps, attachments, and logs, making it easier to identify issues and improve the quality of your software.

A testing workflow with Allure Report consists of two steps, both of which can be done locally, without sending anything over the wire:
   Tests --------------->  Test results  ---------------------> HTML Report

1) Collection phase: while the tests are running, the test framework writes their results into a file or directory.
2) Visualization phase: the Allure Report command-line utility reads the test results and builds an HTML report.
Once the HTML report is generated, you can safely remove the test results — for example, as part of a cleaning procedure before the next test run.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
To get the status of a test in Selenium Java during the teardown phase, you must use the built-in capability of your testing framework (TestNG or JUnit), as Selenium WebDriver itself does not track test execution states.

Using TestNG (@AfterMethod)In TestNG, you can inject the ITestResult object directly into your @AfterMethod teardown function.

TestNG will automatically populate this object with the execution metadata of the test that just finished.

Annotation	Purpose
@Epic	Big module / system area
@Feature	Functionality inside epic
@Story	Specific user story
@Description	Test explanation
@Severity	Importance (CRITICAL, NORMAL, etc.)
@Step	Actions inside test

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Use Tools of Apps:

1. ScriptRunner for Jira:
The must-have app for Jira admins of every technical background: the ultimate toolkit for unlimited automation and customisation

2. There are 6 widely recognized test automation frameworks, each with a unique architecture and distinct advantages and drawbacks.
When creating a test plan, the key lies ( يكمن) in selecting the framework that best meets your specific needs.
  - Linear Automation Framework
  - Modular-Based Testing Framework
  - Library Architecture Testing Framework
  - Data-Driven Framework
  - KeyWork-Driven Framework
 - Hybrid Testing Framework

3. Benefits of Automation Framework
- Enhanced productivity: Test automation frameworks streamline the automation of test cases, saving time and boosting productivity.

- Improved accuracy: By minimizing human errors, automation frameworks contribute to an overall increase in testing accuracy.

- Reusability: The ability to reuse test cases through automation frameworks results in significant time and cost savings.

- Cost reduction: Automation frameworks cut down testing costs by eliminating the need for manual testing.

- Improved test coverage: Automation frameworks ensure thorough testing of all aspects of the software.

- Better test organization: Automation frameworks contribute to a well-organized testing structure, making locating and troubleshooting issues easier.

- Simplified maintenance: Automation frameworks simplify maintenance using the same test cases across multiple software versions.

4. SELENIUM:
- Cross-Browser Testing: Selenium facilitates cross-browser testing, allowing users to validate their applications across various web browsers. It offers compatibility with popular browsers such as Chrome, Firefox, Safari, and Internet Explorer.

- Support for Multiple Languages: Selenium proves to be a robust tool for automating tests on web applications. Testers have the flexibility to script tests in widely used languages like Java, Python, and C# and execute them across diverse browsers.

- Multi-Platform Compatibility: Tests created using Selenium are versatile and can be executed seamlessly on different platforms, including Windows, Mac OS X, and Linux.

- Parallel Test Execution: Leveraging Selenium Grid, users can execute tests concurrently, providing the ability to run multiple tests simultaneously.

- Pricing: Selenium is a free, open-source automation testing tool emphasizing accessibility and cost-effectiveness.

5. Appium: serves as a test automation tool designed for Android and iOS applications.

-  Versatile Language Support: Appium is adaptable to multiple programming languages, such as Java, Objective-C, C#, PHP, Python, and Ruby, as long as the selected language has a   Selenium client library.

-  Cross-Platform Compatibility: Appium facilitates the automation of tests for mobile applications across iOS, Android, and Windows platforms.

-  Pricing: Appium is available as a free, open-source automation testing tool, emphasizing accessibility and cost-effectiveness.

6. Cucumber empowers developers to efficiently create automated tests by utilizing user stories written in plain language that can be easily translated into computerized tests.
The tool employs a user-friendly syntax expressed in plain language text called Gherkin, designed to be easily understandable for non-programmers and allow them to understand the   application’s behavior.

- Cross-Platform Compatibility: Cucumber is compatible with multiple platforms, including Windows, Mac, and Linux.

- Language Support: Cucumber accommodates various programming languages, such as Java, Ruby, and Python.

- Reporting: Cucumber provides extensive reporting of test results, facilitating the identification of areas that may require improvement.

- Pricing: Cucumber is an open-source tool, making it freely accessible to users.

7. Cypress: A modern front-end testing tool crafted (مصممة) using JavaScript, it focuses on simplifying the testing process for developers and QA Teams. It employes as distinctive DOM
manipulation technique operates directly within the browser, providing a more accessible testing approach to developers.

- Effortless Setup: Cypress boasts a quick and straightforward installation and configuration process.

- Interactive Test Runner: Featuring an interactive test runner, Cypress offers rapid feedback on test outcomes.

- Automatic Waiting: Cypress allows for the automatic synchronization of commands and assertions, eliminating the need for additional waits and sleeps in tests.

- Multi-Browser Support: Cypress supports the execution of tests across various browsers, including Chrome, Firefox, and Edge.

- Integration with CI: Cypress seamlessly integrates with continuous integration services such as Jenkins, Travis CI, and CircleCI.

- Pricing: Cypress is not available as a free tool; it is a commercial solution with a pricing plan requiring the purchase of a license.

8. Robot Framework:
An open-source automated testing tool for acceptance testing (AT), acceptance test-driven development (ATDD), and robotic process automation (RPA).
I support a wide range of test libraries and easily extends to custom ones.

- Keyword-Driven Approach: This is a test automation methodology in which keywords can be written in plain English in a tabular syntax format, making them easy to define and reuse in multiple test cases.

- Cross-Platform Support: Offering extensive support across platforms like Windows, Linux, and macOS.

- Cross-functional Testing: Allow simultaneous testing of an application or system in different aspects.

- Integration: The Robot Framework can be integrated with other frameworks, systems, and tools, such as Selenium, Appium, Jenkins, etc.

9. Karate Labs - Unified API, UI & AI Test Automation

Karate Labs
https://karatelabs.io
AI-native test automation for API, UI, and performance. The open-source Karate framework plus Karate Agent — our LLM-powered, self-hosted testing agent.


When QA Teams for automation testing choose the right framework, there are soem things to consider:
- your budget for automated testing
- your team's skill set
- the type of application being tested
- your technology stack
- testing requirements

   
