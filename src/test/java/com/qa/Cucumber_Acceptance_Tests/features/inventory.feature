Feature: Swag Labs inventory
  As a shopper
  I want to browse the product catalog
  So that I can see all available products

  Scenario: Le catalogue contient 6 produits
    Given the standard shopper signs in with "standard_user" and password "secret_sauce"
    Then the catalog contains 6 products
