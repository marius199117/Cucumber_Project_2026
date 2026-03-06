Feature: First Test

  @Smoke @Regression
  Scenario: First Test
    Given I Navigate To "https://www.saucedemo.com/inventory.html" Page
    Then I Verify That Products Title Is Displayed

  @Smoke
  Scenario: First Test
    Given I Navigate To "https://www.saucedemo.com/inventory.html" Page
    Then I Verify That Products Title Is Displayed