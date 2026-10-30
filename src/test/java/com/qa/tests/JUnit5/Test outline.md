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
mvn clean test -Pjunit5 "-Dtest=Junit5*"
