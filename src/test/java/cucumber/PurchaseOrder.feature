
@tag
Feature: purchase the order from ecommerece website
  I want to use this template for my feature file
  
 Background:
 Given I landed on ecommerece page
 
  @tag1
  Scenario Outline: Positive test for submitting the order
    Given Logged in with username  <name> and password <password>
    When I addthe product <productname> to cart
    And checkout <productname> and submit the order
        Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page.

    Examples: 
      | name														  | password          |productname
      |redmond@gmail.com		|Iamking@000    | ZARA COAT 3
