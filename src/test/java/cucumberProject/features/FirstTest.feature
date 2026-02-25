Feature: First Test

  @Smoke @Regression
  Scenario: First Test
    Given I Navigate To "https://www.saucedemo.com" Page
    Then I Successfully Login With Valid Username "standard_user" And Valid Password "secret_sauce"