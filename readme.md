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
