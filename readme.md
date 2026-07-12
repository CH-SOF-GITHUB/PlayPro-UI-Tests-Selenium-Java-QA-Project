# PlayPro-UI-Tests-Selenium-Java

**Description :**  
Projet de tests automatisés de l'interface utilisateur pour le site PlayPro utilisant Selenium WebDriver, Cucumber, et Maven.

**Remarque :**  
Ce projet est lancé et développé pour être utilisé dans IntelliJ IDEA.

---

## Objectif

Ce projet vise à automatiser les tests de l'interface utilisateur du site PlayPro afin d'assurer la qualité et la stabilité des fonctionnalités web.

## Technologies utilisées

- **Selenium WebDriver** : Automatisation des navigateurs web
- **Cucumber** : Framework BDD pour la rédaction des scénarios de tests
- **Maven** : Gestionnaire de dépendances et build
- **IntelliJ IDEA** : Environnement de développement intégré (IDE)

## Prérequis

- Java JDK 11 ou supérieur
- IntelliJ IDEA (ou autre IDE compatible avec Maven)
- Maven

## Installation

1. Cloner ce dépôt :

```bash

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

import com.qa.factory.LTConfigCucumber;
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

import static com.qa.tests.LTTestStatus.markTestStatusViaJS;


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
