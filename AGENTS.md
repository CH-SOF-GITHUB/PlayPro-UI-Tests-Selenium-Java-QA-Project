## Browser automation rules for this project

This project uses **Selenium 4** and **Cucumber 7** with Java 17.

### 📚 Documentation & Sources
- Index: https://www.selenium.dev/llms.txt
- Reference: https://www.selenium.dev/documentation/
- Examples: https://github.com/SeleniumHQ/seleniumhq.github.io/tree/trunk/examples
- Fetch the relevant documentation page before using a Selenium API you are not certain about. Do not use legacy or CDP pages.

### 🚗 Drivers and Browsers
- **Selenium Manager** (built-in with Selenium 4) handles driver binaries automatically.
- Do **not** add third-party driver-manager dependencies (like WebDriverManager), do **not** download drivers manually, and do **not** set paths to driver binaries in code.
- Browser configuration must use native Options classes (e.g., `ChromeOptions`). Do not use `DesiredCapabilities`.

### ⏱️ Waiting Strategies
- **Never use `Thread.sleep()` in a test.**
- Use explicit waits (`WebDriverWait` with `ExpectedConditions`) for the specific condition required.
- Do not mix implicit waits and explicit waits in the same session.

### 🎯 Locators
- Prefer `id`, `name`, or stable attributes like `data-test` (as used in Swag Labs).
- Do not write absolute XPath or rely on fragile generated class names.
- Keep page elements encapsulated inside Page Object Model (POM) classes.

### 🧩 Java & Binding Rules
- Timeouts and waits take a `java.time.Duration`, not a `(long, TimeUnit)` pair.
- `DesiredCapabilities` is replaced by browser-specific `Options`.
- Ensure clean test lifecycles: one fresh browser session per test, and always call `driver.quit()` in hooks/teardown.
