@SmokeTest
Feature: Parallel Browser Execution

  Scenario Outline: Launch multiple browsers and perform actions on the homepage
    Given I launch the "<browser>" browser
    When I navigate to the homepage
    Then I verify that the page title is displayed correctly
    And I click on "Order Online Now"
    And I enter the address and pin code "600102", selecting the first suggestion
    And I navigate to the Veg Pizza section to add "Primavera Gourmet-Pizza" with a quantity of 2
    And add 2 "Margherita" pizzas and 2 "Peppy Paneer" pizzas to my cart
    And go to Beverages section and select "Pepsi 475ml" with a quantity of 12
    Then I verify the sum of each product's quantities and their total amount
    And I remove 1 "Margherita" pizza from my cart
    And remove 6 "Pepsi 475ml" from my cart
    Then verify that the cart subtotal matches the sum of the remaining products values and quantities
    And checkout and validate subtotal

    Examples:
      | browser |
      | chrome  |
      
      