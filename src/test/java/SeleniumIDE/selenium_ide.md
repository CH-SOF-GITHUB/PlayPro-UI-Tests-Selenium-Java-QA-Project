SELENIUM IDE: Developed by Shinya Kastani in 2006
Firefox add-on that helps create tests
Easy-to-use interface to build automated test scripts
Records user interactions on the browser and exports them as a reusable script
Generallu used a prototyping tool
Advancements with new Selenium IDE: Re-usability of tst scripts, Debugging the scripts, Selenium side runner, Provision for control flow statements,
Improved locator functionality.
Limitations of Selenium IDE: Cannot export to WebDriver scripts yet; Does not support data-driven yet; cannot perform database testing;
cannot provide detailed test reports;

Command Line Runner
Documentation:https://docs.seleniumhq.org/selenium-ide/docs/en/introduction/command-line-runner/
To install:
- You need to install node.js.
  Installer: hups://nodejs.org/en/download/
- Install selenium-side-runner
  npm install -g selenium-side-runner
  check the installation : selenium-side-runner --version.
  OS                  Windows
  npm global folder   C:\Users\chaker\AppData\Roaming\npm\node_modules
  C:\Users\chaker\Downloads\Selenium IDE>npm root -g
  C:\Users\chaker\AppData\Roaming\npm\node_modules
  Selenium Runner     4.0.13
- Download & Install the browser drivers.
  ‘browser driver download : hitps://www.seleniumhq.ong/download/
  ‘browser driver install: npm install -g chromedriver
  C:\Users\chaker\Downloads\Selenium IDE>npm list -g chromedriver --depth=0
  C:\Users\chaker\AppData\Roaming\npm
  `-- chromedriver@152.0.3
  To Execute:
- selenium-side-runner <projectname.side>
- selenium-side-runner -c "browser Name=chrome"<projectname>
- selenium-side-runner -c "browserName=chrome" --base-url "https://www.google.com/" SimplelearnIDE.side
- selenium-side-runner -c "browserName=chrome" SimplelearnIDE.side  IF You don't have Base URL in your test case
  To Execute in Grid:
- Test Suite Properties -> check Run in Parallel: selenium-side-runner SimplelearnIDE.side		
