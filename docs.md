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

Annotation	      Purpose
@Epic	       Big module / system area
@Feature	   Functionality inside epic
@Story	       Specific user story
@Description   Test explanation
@Severity	   Importance (CRITICAL, NORMAL, etc.)
@Step	       Actions inside test

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



## how to configurate your project with Allure TestNG.
1. Add Allure properties

In your <properties> section, replace:

<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>

with:

<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

    <allure.version>2.29.1</allure.version>
    <aspectj.version>1.9.24</aspectj.version>
</properties>

2. Add Allure BOM

Inside <dependencyManagement>, add this before your Cucumber BOM:

<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-bom</artifactId>
    <version>${allure.version}</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>

Then your dependencyManagement will manage the versions automatically.

3. Add Allure dependencies

Inside <dependencies>, add:

<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-testng</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-java-commons</artifactId>
    <scope>test</scope>
</dependency>

If you use REST Assured later, you can also add:

<dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-rest-assured</artifactId>
    <scope>test</scope>
</dependency>

4. Configure AspectJ
   Your current maven-surefire-plugin must be updated.

Inside the plugin configuration, add:

<argLine>
    -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
</argLine>

and add the plugin dependency:

<dependencies>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>${aspectj.version}</version>
    </dependency>
</dependencies>

So the end of your maven-surefire-plugin should look like:

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>

    <configuration>

        <argLine>
            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
        </argLine>

        ...
    </configuration>

    <dependencies>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${aspectj.version}</version>
        </dependency>
    </dependencies>

</plugin>

5. Create allure.properties

Create the file:

src/test/resources/allure.properties

Content:
allure.results.directory=target/allure-results

6. Execute tests

Run:

mvn clean test

or

mvn clean verify

The folder

target/allure-results

should be created automatically.

7. Generate report
   allure serve target/allure-results

or

allure generate target/allure-results --clean
allure open

8. Then you can use all Allure annotations
   For example:

@Epic("Authentication")
@Feature("Login")
@Story("Successful login")
@Severity(SeverityLevel.CRITICAL)
@Test
public void loginTest() {
...
}

and

@Step("Login with user {username}")
public void login(String username, String password) {
...
}

and

@Attachment(value = "Screenshot", type = "image/png")
public byte[] takeScreenshot() {
return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
}



















git clone https://github.com/CH-SOF-GITHUB/PlayPro-UI-Tests-Selenium-Java.git

## Architecture Cloud et Exécution des tests sur le Cloud (Cross-Browser Testing)

Selon la configuration utilisée, les tests peuvent être exécutés :
- En local (navigateur local)
- Sur LambdaTest Cloud
- Sur TestingBot Cloud
- Exécution des tests sur différents navigateurs (Chrome, Edge, Firefox, etc.)
- Support de plusieurs systèmes d’exploitation (Windows, macOS)
- Gestion des tests à distance via RemoteWebDriver
- Visualisation des vidéos, logs et screenshots dans le dashboard LambdaTest

➡️ Idéal pour valider la compatibilité multi-navigateurs et paralléliser les tests.
➡️ La sélection de la plateforme se fait via des classes java de configuration dédiées


## 
Commandes pour lancer localement (Git Bash / MINGW64) et voir le navigateur :  
export HEADLESS=false && node quickStart.js
Sous Windows (cmd) :  
set HEADLESS=false && node quickStart.js
Sous PowerShell :  
$env:HEADLESS = "false"; node quickStart.js
En CI garder HEADLESS=true (ou ne pas définir) et utiliser xvfb/headless.


# Mettre à jour les refs distantes
git fetch origin

# Basculer sur main pour vérifier l'existence du dossier
git checkout main
ls -la src/test/java/js

# Si le dossier existe sur main, revenir sur ta branche de travail
git checkout circleci-project-setup

# Copier le dossier `src/test/java/js` depuis la branche main vers la branche courante
git checkout main -- src/test/java/js

# Ajouter, committer et pousser les fichiers sur origin/circleci-project-setup
git add src/test/java/js
git commit -m "Import JS Selenium tests from main"
git push -u origin circleci-project-setup


# Ensuite, tu pourras créer une Pull Request pour
Merge 'circleci-project-setup' into 'main'

clean test -Dsurefire.suiteXmlFiles=src/test/resources/xml/testng.xml



# mvn clean test -Dtest=Runners.TestRunner -Dcucumber.filter.tags="@SampleGoogleSearch"
# TestRunner: run: mvn -B -Dtest=Runners.TestRunner test  OR  mvn -B clean test -Pdev -Dtest=Runners.TestRunner
# -B à la commande Maven dans ton workflow. Cela garantit que Maven fonctionne en mode non interactif,
# ce qui est recommandé dans les environnements CI comme GitHub Actions.
# mvn -B test -Dsurefire.suiteXmlFiles=/home/runner/work/PlayPro-UI-Tests-Selenium-Java/PlayPro-UI-Tests-Selenium-Java/src/test/resources/suites/testng.xml
# mvn -B -Dtest=tests.PlayPro.DEV.Edge.LambdaTest.Reservations.TC03 test
# Optional: désactivé car provoque 403
# - name: Update dependency graph
#   uses: advanced-security/maven-dependency-submission-action@571e99aab1055c2e71a1e2309b9691de18d6b7d6
#   env:
#     GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}



###########################################################################################################################################################
How best to confgurate POM.XML:
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>
<groupId>org.selenide.examples</groupId>
<artifactId>selenide-cucumber</artifactId>
<version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.source>25</maven.compiler.source>
    <maven.compiler.target>25</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <surefireVersion>3.5.5</surefireVersion>
    <cucumberVersion>7.34.3</cucumberVersion>
    <selenideVersion>7.16.1</selenideVersion>
  </properties>

  <build>
    <defaultGoal>test</defaultGoal>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>${surefireVersion}</version>
        <configuration>
          <systemPropertyVariables>
            <selenide.downloadsFolder>target/downloads</selenide.downloadsFolder>
          </systemPropertyVariables>
        </configuration>
      </plugin>
    </plugins>
  </build>

  <dependencies>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-simple</artifactId>
      <version>2.0.18</version>
    </dependency>
    <dependency>
      <groupId>io.cucumber</groupId>
      <artifactId>cucumber-java</artifactId>
      <version>${cucumberVersion}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>io.cucumber</groupId>
      <artifactId>cucumber-junit</artifactId>
      <version>${cucumberVersion}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13.2</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>com.codeborne</groupId>
      <artifactId>selenide-junit4</artifactId>
      <version>${selenideVersion}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>com.codeborne</groupId>
      <artifactId>selenide-video-recorder</artifactId>
      <version>${selenideVersion}</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <reporting>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-report-plugin</artifactId>
        <version>${surefireVersion}</version>
      </plugin>
    </plugins>
  </reporting>
</project>
##############################################################################################################################
@BeforeMethod
    public void setUp() {

        log.info("Starting WebDriver...");

        ChromeOptions options = new ChromeOptions();

        // =========================
        // CI + LOCAL COMPATIBILITY FIX
        // =========================
        String chromeBinary = System.getenv("CHROME_BINARY");
        if (chromeBinary != null && !chromeBinary.isEmpty()) {
            options.setBinary(chromeBinary);
        }

        // =========================
        // STABLE CI ARGUMENTS
        // =========================
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        // =========================
        // INIT DRIVER
        // =========================
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        Wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // =========================
        // PAGE OBJECT INIT
        // =========================
        webReservationPage = new WebReservationPage(driver);
        webEXP1Page = new WebEXP1Page(driver);
        webEXP2Page = new WebEXP2Page(driver);

        log.info("Navigating to application...");

        driver.get("https://demotenant.playpro.fr/connexion");
    }
########################################################################################################################################################
package com.qa.cucumber.bdd;

import com.qa.configuration.LTConfigCucumber;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static com.qa.LTTestStatus.markTestStatusViaJS;


public class Hooks extends LTConfigCucumber {
private static final Log log = LogFactory.getLog(Hooks.class);
// initialize the web driver
// public static WebDriver driver = null;
public static WebDriver driver = null;

    @Before
    public void setUp() {
        log.info("🚀 Starting WebDriver For Cucumber BDD...");
        try {
            driver = getCucumberDriver();
            if (driver == null) {
                log.error("❌ Driver initialization failed: driver is null");
                throw new RuntimeException("Driver initialization failed: driver is null");
            }
            driver.manage().window().maximize();
            log.info("Navigating to URL...");
            driver.navigate().to("https://demotenant.playpro.fr/connexion");
        } catch (Exception e) {
            log.error("❌ Driver initialization failed: " + e.getMessage());
            throw e;
        }
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        log.info("\n 🚀 Le Scenario Testé: " + scenario.getName() + " Et le status : " + scenario.getStatus());
        if (scenario.getStatus() == Status.FAILED) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("C:\\Users\\chaker\\Desktop\\automation\\Mobile-Web-Testing\\MobileWebTesting\\src\\test\\java\\BDD\\Screenshots\\failure\\" + scenario.getName() + ".png");
            FileUtils.copyFile(srcFile, targetFile);
        }
        // declare a method to set the test status in LambdaTest via JS
        markTestStatusViaJS(driver, scenario.getStatus() == Status.PASSED, "Scenario " + scenario.getName() + " " + (scenario.getStatus() == Status.PASSED ? "passed" : "failed"));
        if (driver != null) {
            log.info("\n 🚀 Closing WebDriver For Cucumber BDD...");
            driver.quit();
        }
    }
}
##############################################################################################################################
##############################################################################################################################
playwright.yml:
#name: Playwright Tests
#on:
#  push:
#    branches: [ "main" ]
#  pull_request:
#    branches: [ "main" ]
#jobs:
#  test:
#    timeout-minutes: 60
#    runs-on: ubuntu-latest
#    steps:
#      - uses: actions/checkout@v4
#      - uses: actions/setup-node@v4
#        with:
#          node-version: lts/*
#      - name: Install dependencies
#        run: npm ci
#        working-directory: src/test/java/Playwright
#      - name: Install Playwright Browsers
#        run: npx playwright install --with-deps
#        working-directory: src/test/java/Playwright
#      - name: Run Playwright tests
#        run: npx playwright test
#        working-directory: src/test/java/Playwright
#      - uses: actions/upload-artifact@v4
#        if: ${{ !cancelled() }}
#        with:
#          name: playwright-report
#          path: src/test/java/Playwright/playwright-report/
#          retention-days: 30

##############################################################################################################################
##############################################################################################################################
selenide-ci.yaml:
#name: Selenide UI Tests
#
#on:
#  push:
#    branches: [ "main" ]
#  pull_request:
#    branches: [ "main" ]
#
#jobs:
#  selenide-tests:
#    runs-on: ubuntu-latest
#
#    steps:
#      - uses: actions/checkout@v4
#
#      - name: Set up JDK 17
#        uses: actions/setup-java@v4
#        with:
#          java-version: '17'
#          distribution: 'temurin'
#          cache: maven
#
#      - name: Build project
#        run: mvn clean compile
#
#      - name: Run Selenide tests
#        run: mvn "-Dtest=Selenide.UI.PlayPro.Web.Front.**" test
#
#      - name: Upload Selenide Reports
#        if: always()
#        uses: actions/upload-artifact@v4
#        with:
#          name: selenide-reports
#          path: target/selenide-ci-reports

##############################################################################################################################
##############################################################################################################################
java-ci.yaml:
name: Java CI with Maven

on:
push:
branches: [ "main" ]
pull_request:
branches: [ "main" ]

jobs:
build:
runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: maven

      - name: Set build name
        run: |
          BUILD_NAME="PR-${{ github.event.pull_request.number }}-${{ github.sha }}"
          echo "BUILD_NAME=$BUILD_NAME" >> $GITHUB_ENV

      - name: Set Repository Name
        run: |
          REPO_NAME="${{ github.repository }}"
          echo "REPO_NAME=$REPO_NAME" >> $GITHUB_ENV

      - name: Set github run ID
        run: |
          GITHUB_RUN_ID="${{ github.run_id }}"
          echo "GITHUB_RUN_ID=$GITHUB_RUN_ID" >> $GITHUB_ENV  

      - name: Clean and build project
        run: mvn clean compile

      # JAVA TESTS UNIQUEMENT
      - name: Run Specific Suite (test cases - selenium java)
        env:
          CI: true
        run: mvn test -Dheadless=true -Dtest=com.qa.tests.PlayPro.DEV.LambdaTest.Chrome.Login.TC001




POM/XML:
build:
<build>
<plugins>
<plugin>
<!-- Compiles Java source code -->
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-compiler-plugin</artifactId>
<version>3.11.0</version> <!-- ajouter une version -->
<configuration>
<source>11</source>
<target>11</target>
<!--
This is a very common configuration:
Develop with the latest JDK (21)
Produce code compatible with Java 11
This allows your tests to run on environments that only have Java 11.

                    When should you change to 17?
                    Only if you actually need Java 17 features or your team has decided to standardize on Java 17.
                    Examples:
                       - Pattern matching
                       - Records
                       - Sealed classes
                       - Other Java 17 language/API features
                       - For a Selenium automation framework, most teams don't need these features.

                    Apache Maven : 3.9.9
                    JDK          : 21.0.9 (Oracle)
                    Maven Plugin : maven-compiler-plugin 3.11.0
                    pom.xml      : source=11 / target=11

                    Replace <source>/<target> with <release>?  Yes, recommended if the tester decided to do this
                    -->
                    <compilerArgs>
                        <arg>-parameters</arg>
                    </compilerArgs>
                </configuration>
            </plugin>
            <plugin>
                <!-- Runs TestNG/JUnit tests -->
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <configuration>
                    <!-- keep your cucumber config -->
                    <systemPropertyVariables>
                        <!--suppress UnresolvedMavenProperty -->
                        <cucumber.filter.tags>${cucumber.filter.tags}</cucumber.filter.tags>
                    </systemPropertyVariables>
                    <!-- ✅ Force JUnit format (for CircleCI) -->
                    <properties>
                        <property>
                            <name>junit</name>
                            <value>true</value>
                        </property>
                    </properties>
                    <!-- ✅ IMPORTANT: show logs in console -->
                    <useFile>true</useFile>
                    <!-- ✅ show more details -->
                    <printSummary>true</printSummary>
                    <!-- optional -->
                    <reportsDirectory>${project.build.directory}/surefire-reports</reportsDirectory>
                    <!-- Add the following options of allure report to your maven-surefire-plugin -->
                    <argLine>
                        -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
                    </argLine>
                </configuration>
            </plugin>
            <plugin>
                <groupId>dev.aspectj</groupId>
                <artifactId>aspectj-maven-plugin</artifactId>
                <version>1.14</version>
                <dependencies>
                    <dependency>
                        <groupId>org.aspectj</groupId>
                        <artifactId>aspectjtools</artifactId>
                        <version>${aspectj.version}</version>
                    </dependency>
                </dependencies>
                <configuration>
                    <complianceLevel>17</complianceLevel>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>test-compile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
##################################################################################################################################################
.gitlab-ci.yml:
# This file is a template, and might need editing before it works on your project.
# This is a sample GitLab CI/CD configuration file that should run without any modifications.
# It demonstrates a basic 3 stage CI/CD pipeline. Instead of real tests or scripts,
# it uses echo commands to simulate the pipeline execution.
#
# A pipeline is composed of independent jobs that run scripts, grouped into stages.
# Stages run in sequential order, but jobs within stages run in parallel.
#
# For more information, see: https://docs.gitlab.com/ee/ci/yaml/#stages
#
# You can copy and paste this template into a new `.gitlab-ci.yml` file.
# You should not add this template to an existing `.gitlab-ci.yml` file by using the `include:` keyword.
#
# To contribute improvements to CI/CD templates, please follow the Development guide at:
# https://docs.gitlab.com/development/cicd/templates/
# This specific template is located at:
# https://gitlab.com/gitlab-org/gitlab/-/blob/master/lib/gitlab/ci/templates/Getting-Started.gitlab-ci.yml
image:
name: cypress/included:15.18.1  # This image contains all the dependencies needed to run Cypress tests.
entrypoint: [ "" ]

stages: # List of stages for jobs, and their order of execution
- build
- test
- deploy

build-job: # This job runs in the build stage, which runs first.
stage: build
script:
- echo "Compiling the code..."
- echo "Compile complete."

unit-test-job: # This job runs in the test stage.
stage: test    # It only starts when the job in the build stage completes successfully.
script:
- echo "Running unit tests... This will take about 60 seconds."
- sleep 60
- echo "Code coverage is 90%"

lint-test-job: # This job also runs in the test stage.
stage: test    # It can run at the same time as unit-test-job (in parallel).
script:
- echo "Linting code... This will take about 10 seconds."
- sleep 10
- echo "No lint issues found."

cypress-test-job:
stage: test
script:
- echo "Début du test Cypress"
# Exécution ciblée du test
- cd src/test/java/Cypress
- npx cypress run --spec "cypress/e2e/play/pro/v3/webservice/add_to_basket_01_spec.cy.js"

artifacts:
when: always
paths:
- src/test/java/Cypress/cypress/screenshots
- src/test/java/Cypress/cypress/videos
expire_in: 1 week

deploy-job: # This job runs in the deployment stage.
stage: deploy  # It only runs when *both* jobs in the test stage complete successfully.
environment: production
script:
- echo "Deploying application..."
- echo "Application successfully deployed."
