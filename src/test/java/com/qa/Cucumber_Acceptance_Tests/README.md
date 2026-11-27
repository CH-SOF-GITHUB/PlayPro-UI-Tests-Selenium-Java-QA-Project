# Cucumber Acceptance Test Structure

This scaffold separates acceptance specifications from the Java automation glue:

- `features/` — Gherkin grouped by business capability (authentication, inventory, cart and checkout).
- `runners/` — Cucumber runner entry points and suite configuration.
- `steps/` — step definitions grouped by domain; steps call page objects and reusable domain helpers.
- `hooks/` — scenario lifecycle setup and teardown, including a fresh maximized browser and `driver.quit()`.
- `pages/` and `components/` — Selenium locators and browser interactions, following the project's Page Object Model rules.
- `context/` — scenario-scoped state; avoid state shared across scenarios.
- `config/`, `data/`, and `testdata/` — environment settings, data builders, and small non-secret test fixtures.
- `utils/` — shared technical helpers only.
- `reports/` and `screenshots/` — generated outputs; keep them out of version control as appropriate.

Write scenarios in business language around observable behavior. Use explicit waits for specific conditions, avoid implicit waits and `Thread.sleep()`, and keep Selenium interactions inside page objects/components.

This project keeps its executable `.feature` files in `src/test/java/com/qa/Cucumber_Acceptance_Tests/features`; do not move them to `src/test/resources`. Keep Java classes in the corresponding `steps/`, `pages/`, `hooks/`, and other scaffold folders, using valid Java package names such as `com.qa.Cucumber_Acceptance_Tests.steps`.

## Architecture (ASCII)

```text
+---------------------------------------------------------------+
|              Cucumber BDD Acceptance Test Framework            |
+---------------------------------------------------------------+

  BUSINESS SPECIFICATION                 EXECUTION ENTRY POINT
  +---------------------------+           +---------------------------+
  | features/                 |           | runners/                  |
  |   authentification.feature|           |   RunCucumberTest.java    |
  |   Given / When / Then     |---------->|   @Suite (JUnit 5)        |
  +---------------------------+           |   @IncludeEngines(cucumber)|
                                          +-------------+-------------+
                                                        |
                                                        v
                                          +---------------------------+
                                          | Cucumber JUnit Platform   |
                                          | Engine + Suite Engine     |
                                          | glue / tags / plugins     |
                                          +-------------+-------------+
                                                        |
              +-----------------------------------------+----------------+
              |                                         |                |
              v                                         v                v
  +---------------------------+           +---------------------------+  +---------------------------+
  | hooks/                    |           | steps/                    |  | context/                  |
  | @Before / @After          |           | AuthenticationSteps, ...  |  | BrowserSession            |
  | fresh maximized browser   |           | domain step definitions   |  | scenario-scoped state     |
  | driver.quit()             |           +-------------+-------------+  +---------------------------+
  +-------------+-------------+                         |
                |                                       v
                |                         +---------------------------+
                |                         | pages/ + components/      |
                |                         | POM: locators + actions   |
                |                         | explicit waits only       |
                |                         +-------------+-------------+
                |                                       v
                +-------------------------> +---------------------------+
                                            | Browser (Chrome)          |
                                            | application under test    |
                                            +-------------+-------------+
                                                          v
                                            +---------------------------+
                                            | reports/ + screenshots/   |
                                            | target/cucumber-acceptance|
                                            +---------------------------+

  SUPPORTING INPUTS (never inside feature files)
  config/acceptance.properties (swagLabs.url)
  data/   testdata/   utils/   -> feed steps/pages only
```

Run the acceptance suite with:

```shell
mvn -Pjunit5 test -Dtest=com.qa.Cucumber_Acceptance_Tests.runners.RunCucumberTest
```
