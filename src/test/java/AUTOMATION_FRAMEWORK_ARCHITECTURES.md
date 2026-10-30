Selenium WebDriver – Instantiation Architecture

                 SELENIUM WEBDRIVER INSTANTIATION
                              |
                              v
                 +--------------------------+
                 |  Create WebDriver Object |
                 |   new ChromeDriver()     |
                 +--------------------------+
                              |
                              v
                 +--------------------------+
                 |   How to manage browser  |
                 |        driver?           |
                 +--------------------------+
                              |
          +-------------------+-------------------+
          |                   |                   |
          v                   v                   v
+-------------------+ +------------------+ +----------------------+
|  METHOD 1         | |  METHOD 2        | |  METHOD 3            |
|                   | |                  | |                      |
| Driver Path       | | Environment      | | Selenium Manager     |
|                   | | Variable (PATH)  | | (Selenium 4.6+)      |
+-------------------+ +------------------+ +----------------------+
         |                   |                   |
         v                   v                   v
+-------------------+ +------------------+ +----------------------+
| System.setProperty| | Driver executable| | Automatic driver     |
|                   | | available in PATH| | management           |
| webdriver.chrome  | |                  | |                      |
| .driver           | | No explicit path | | No explicit path     |
+-------------------+ +------------------+ +----------------------+
          |                   |                   |
          +-------------------+-------------------+
                              |
                              v
                 +--------------------------+
                 |    ChromeDriver          |
                 |    new ChromeDriver()    |
                 +--------------------------+
                              |
                              v
                 +--------------------------+
                 |       WebDriver          |
                 |   Browser Instance       |
                 +--------------------------+
                              |
                              v
                +--------------------------+
                |       Browser            |
                |     Google Chrome        |
                +--------------------------+

#### Method 1 – Browser-Specific Driver Path
Cette méthode consiste à spécifier manuellement le chemin exact de l'exécutable WebDriver dans le code Java à l'aide de System.setProperty().
// Set ChromeDriver executable path
System.setProperty("webdriver.chrome.driver","C:\\WebDrivers\\chromedriver.exe");

                BROWSER-SPECIFIC DRIVER PATH
                            |
                            v
                +----------------------+
                |   Download Driver    |
                |                      | 
                |   chromedriver.exe   |
                +----------------------+
                           |
                           v
                +----------------------+
                |   Store Driver       |
                |                      |
                |   C:\WebDrivers\     |
                +----------------------+
                          |
                          v
                +----------------------+
                |   System.setProperty |
                |                      |
                | webdriver.chrome     |
                | .driver               |
                +----------------------+
                          |
                          v
                +----------------------+
                |   ChromeDriver       |
                |                      |
                |   new ChromeDriver() |
                +----------------------+
                          |
                          v
                +----------------------+
                |   WebDriver Instance |
                +----------------------+
                         |
                         v
                +----------------------+
                |   Google Chrome      |
                +----------------------+

#### Method 2 – Environment Variable (PATH)
Cette méthode consiste à ajouter le dossier contenant le WebDriver dans la variable d'environnement PATH de Windows.
// Create WebDriver instance
WebDriver driver = new ChromeDriver();

                 ENVIRONMENT VARIABLE (PATH)
                              |
                              v
                 +--------------------------+
                 |     Windows PATH         |
                 |                          |
                 |     C:\WebDrivers\       |
                 +--------------------------+
                              |
                              v 
                 +--------------------------+
                 |     chromedriver.exe     |
                 |     geckodriver.exe      |
                 |     msedgedriver.exe     |
                 |  New Path In Environment |
                 |       Variables          |
                 +--------------------------+
                              |
                              v
                 +--------------------------+
                 |    Selenium Java API     |
                 |                          |
                 |  new ChromeDriver()      |
                 +--------------------------+
                              |
                              v
                 +--------------------------+
                 |      Google Chrome       |
                 +--------------------------+

#### Selenium Manager (Selenium 4.6+)
// Create WebDriver instance
WebDriver driver = new ChromeDriver();

              SELENIUM MANAGER WORKFLOW
                         |
                         v
              +----------------------+
              |   Java Test Script   |
              |                      |
              | new ChromeDriver()   |
              +----------------------+
                         |
                         v
              +----------------------+
              |   Selenium Binding   |
              |                      |
              |  Selenium Java API   |
              +----------------------+
                         |
                         v
              +----------------------+
              |   Selenium Manager   |
              |                      |
              |  Detect Browser      |
              |  Detect Version      |
              +----------------------+
                         |
                         v
              +----------------------+
              |  Check Driver        |
              |  Availability        |
              +----------------------+
                         |
                +--------+--------+
                |                 |
                v                 v
          +-----------+     +----------------+
          | Driver    |     | Driver Missing |
          | Available |     | or Incompatible|
          +-----------+     +----------------+
                |                 |
                |                 v
                |          +----------------+
                |          | Download       |
                |          | Compatible     |
                |          | ChromeDriver   |
                |          +----------------+
                |                 |
                |                 v
                |          +----------------+
                |          | Cache Driver   |
                |          +----------------+
                |                 |
                +-----------------+
                         |
                         v
              +----------------------+
              |   Create Driver      |
              |  ChromeDriver        |
              +----------------------+
                         |
                         v
              +----------------------+
              |   Launch Chrome      |
              +----------------------+

#### Alternative: WebDriverManager (Third-party library)
WebDriverManager est une 4 éme approche. Contrairement à Selenium Manager, il s'agit d'une bibliothèque externe développée par Boni García.
bibliothèque externe Dans pom.xml : io.github.bonigarcia - webdrivermanager
// 
WebDriverManager.chromedriver().setup();

                       WEBDRIVERMANAGER APPROACH
                                  | 
                                  v
                     +--------------------------+
                     |       pom.xml            |
                     |                          |
                     |  selenium-java           |
                     |  webdrivermanager        |
                     +--------------------------+
                                  |
                                  v
                     +--------------------------+
                     | WebDriverManager         |
                     |                          |
                     | WebDriverManager         |
                     | .chromedriver().setup()  |
                     +--------------------------+
                                 |
                                 v
                     +--------------------------+
                     | Download / Configure     |
                     | ChromeDriver             |
                     +--------------------------+
                                 |
                                 v
                     +--------------------------+
                     | new ChromeDriver()       |
                     +--------------------------+
                                |
                                v
                     +--------------------------+
                     | WebDriver + Chrome       |
                     +--------------------------+
