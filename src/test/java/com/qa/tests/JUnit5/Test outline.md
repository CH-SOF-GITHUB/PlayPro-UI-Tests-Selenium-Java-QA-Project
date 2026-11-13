Test Outline: Swag Labs - Inventory Reached After Login and Product Selection
Overview
Application: SauceDemo-SwagLabs (SauceDemo-SwagLabs)
Deployment: SauceDemo-SwagLabs - Demo (https://www.saucedemo.com, Deployment)
Credentials: saucedemo-standard-user (saucedemo-standard-user)
1. Log in to Swag Labs
   Action: Navigate to https://www.saucedemo.com (app.url).
   Action: Focus the username input field and enter {{@app.defaults.username}}.
   Action: Focus the password input field and enter {{@app.defaults.password}}.
   Action: Click the "Login" button (#login-button).
   Validation:
   Wait until the page loads and reaches /inventory.html.
2. Verify Inventory Page is Reached
   Action: Verify the inventory header and container are present.
   Validation:
   Assert URL contains /inventory.html.
   Assert the title header contains Products (.title element).
   Assert the inventory container (#inventory_container / .inventory_list) is present.
3. Interact with Product Filter / Category Selection
   Action: Locate the product filter / sort dropdown (.product_sort_container) or select a product category/item from the list.
   Action: Select a filter/sort option (e.g., "Price (low to high)" or "Name (A to Z)") or click on a product item link (such as "Sauce Labs Backpack").
   Validation:
   If filter/sort selected: Assert the active option reflects the chosen filter (e.g. .active_option displays selected sort/filter).
   If product item clicked: Assert the product details page opens, URL contains /inventory-item.html, and the item title is displayed.
4. Verify Inventory State and Navigation
   Action: If product detail was opened, click the "Back to products" button (#back-to-products).
   Validation:
   Assert the user is returned to /inventory.html.
   Assert the "Products" header is visible.


cmd all test of package:
mvn clean test -Pjunit5 "-Dtest=Junit5Inventory2Tests"
mvn clean test -Pjunit5 "-Dtest=Junit5*"

set TESTOMATIO=tstmt_PIYAdyOv5Zm3b6bY4WOw_vk49NLsdZ-HiQ1793099007&& mvn test -Pjunit5 "-Dtest=Junit5Inventory1Tests"
$env:TESTOMATIO=tstmt_PIYAdyOv5Zm3b6bY4WOw_vk49NLsdZ-HiQ1793099007; mvn test -Pjunit5 "-Dtest=Junit5Inventory1Tests"

$env:TESTOMATIO="tstmt_PIYAdyOv5Zm3b6bY4WOw_vk49NLsdZ-HiQ1793099007"; mvn test -Pjunit5 "-Dtest=Junit5InvalidLoginTests"



JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

The JUnit Platform serves as a foundation for launching testing frameworks on the JVM. 
It also defines the TestEngine API for developing a testing framework that runs on the platform. 
Furthermore, the platform provides a Console Launcher to launch the platform from the command line and a JUnit 4 based Runner 
for running any TestEngine on the platform in a JUnit 4 based environment. 
First-class support for the JUnit Platform also exists in popular IDEs (see IntelliJ IDEA, Eclipse, NetBeans, and Visual Studio Code) 
and build tools (see Gradle, Maven, and Ant).

JUnit Jupiter is the combination of the new programming model and extension model for writing tests and extensions in JUnit 5. 
The Jupiter sub-project provides a TestEngine for running Jupiter based tests on the platform.

JUnit Vintage provides a TestEngine for running JUnit 3 and JUnit 4 based tests on the platform.


JUnit 5 (également connu sous le nom de JUnit Jupiter) est le framework de tests unitaires de référence pour Java et la JVM (nécessite Java 8 ou supérieur).
Architecture de JUnit 5:
L'écosystème JUnit 5 se divise en trois sous-projets principaux :
• JUnit Platform : Le socle d'exécution qui permet de lancer les frameworks de test sur la JVM (via IDE ou outils de build comme Maven/Gradle).
• JUnit Jupiter : Le nouveau modèle de programmation et d'extension pour écrire des tests et des assertions.
• JUnit Vintage : Un moteur de compatibilité permettant d'exécuter les anciens tests écrits en JUnit 3 ou 4.


Quick Reference:
Task	        Command
Run all	     mvn test or ./gradlew test
Run class	 mvn test -Dtest=UserServiceTest
Run method	 mvn test -Dtest=UserServiceTest#createUser_succeeds
Run tagged	 @Tag("slow") + mvn test -Dgroups="slow"
Disable	     @Disabled("Reason")
Conditional	 @EnabledOnOs(OS.LINUX)
Timeout	     @Timeout(value = 5, unit = TimeUnit.SECONDS)
Repeated	 @RepeatedTest(5)
Order	     @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
