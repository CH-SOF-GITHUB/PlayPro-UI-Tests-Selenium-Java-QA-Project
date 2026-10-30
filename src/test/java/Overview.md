Selenium Java is an open source framework for automating web browsers.

                         TYPES DE TESTS QA
                                │
              ┌─────────────────┴─────────────────┐
              ↓                                   ↓
┌──────────────────────────────┐  ┌──────────────────────────────┐
│   TESTS AVEC SELENIUM JAVA   │  │  TESTS NON ADAPTÉS À SELENIUM│
└──────────────────────────────┘  └──────────────────────────────┘
       │                                   │
┌──────┼──────┐                     ┌──────┼──────┐
↓      ↓      ↓                     ↓      ↓      ↓
┌──────┐ ┌──────┐ ┌──────────┐      ┌────────┐ ┌───────────┐ ┌─────────┐
│ACCEP-│ │RÉGRES│ │ SYSTÈME  │      │UNITAIRE│ │PERFORMANCE│ │SÉCURITÉ │
│TATION│ │SION  │ │/INTÉGRA- │      └────────┘ └───────────┘ └─────────┘
└──────┘ └──────┘ │  TION    │          │          │            │
│       │         └──────────┘          ↓          ↓            ↓
↓       ↓               │              JUnit /    JMeter /     OWASP ZAP
Parcours  Non-          ↓              TestNG     Gatling /
utilisateur régression  Tests UI                   k6

selenium_automation_framework:
┌──────────────────────────────┐
│          TEST LAYER          │
│     TestNG / JUnit / Cucumber│
└──────────────────────────────┘
↓
┌──────────────────────────────┐
│      PAGE OBJECT LAYER       │
│    LoginPage / HomePage      │
└──────────────────────────────┘
↓
┌──────────────────────────────┐
│      UTILITY / CORE LAYER    │
│ BaseTest / DriverFactory     │
│ WaitUtils / ConfigReader     │
└──────────────────────────────┘
↓
┌──────────────────────────────┐
│      SELENIUM WEBDRIVER      │
│   WebDriver API /ChromeDriver│
└──────────────────────────────┘
↓
┌──────────────────────────────┐
│           BROWSER            │
│         Google Chrome        │
└──────────────────────────────┘
↓
┌──────────────────────────────┐
│    APPLICATION UNDER TEST    │
│        Web Application       │
└──────────────────────────────┘

######

                    SELENIUM WEBDRIVER ARCHITECTURE
       (Client Libraries -> W3C Protocol -> WebDriver -> Browsers)


+----------------------------+       +----------------------------+
|      CLIENT LIBRARIES      |       |        W3C PROTOCOL        |
|                            |       |                            |
|  Java      Python    JS    |------>|  Standard communication    |
|  PHP       C#        C++   | HTTP  |  protocol for WebDriver    |
|                            | JSON  |                            |
+----------------------------+       +----------------------------+
|
| HTTP / JSON
v
+-----------------------------------+
|             WEBDRIVER             |
|                                   |
|  ChromeDriver                     |
|  FirefoxDriver                    |
|  EdgeDriver                       |
|  SafariDriver                     |
|                                   |
|  Translates commands into         |
|  browser-specific actions         |
+-----------------------------------+
|
| WebDriver Commands
v
+-----------------------------------+
|             BROWSERS              |
|                                   |
|  Google Chrome                    |
|  Mozilla Firefox                  |
|  Microsoft Edge                   |
|  Safari                            |
+-----------------------------------+

       +----------------------------------------------------------+
       |                                                          |
       |  HTTP REQUESTS  ---------------------------------------> |
       |                                                          |
       |  HTTP RESPONSES <--------------------------------------- |
       |                                                          |
       +----------------------------------------------------------+
          CLIENT LIBRARIES                         BROWSERS
