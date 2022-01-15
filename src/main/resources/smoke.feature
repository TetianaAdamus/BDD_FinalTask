Feature: Smoke
  As a user
  I want to test all main site functionality
  So that I can be sure that site works correctly

  Scenario Outline: Check product price filter
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User inputs '<minPrice>' value into minimal price filter field
    And User inputs '<maxPrice>' value into maximal price filter field
    And User clicks filter button
    Then User checks all filtered products are within '<minPrice>' and '<maxPrice>'


    Examples:
      | homePage               | keyword     | minPrice | maxPrice |
      | https://www.amazon.com | iphone      | 500      | 1000     |


  Scenario Outline: Check the specified alert message is displayed on the Sign-in page
    Given User opens '<homePage>' page
    And User checks welcome message visibility
    When User clicks welcome message button
    And User inputs incorrect data '<characterSet>' in the 'Sigh-In' field
    Then User checks that '<alertMessage>' is visible


    Examples:
      | homePage                | characterSet    | alertMessage        |
      |https://www.amazon.com   | sdfsda@sdfssd   | There was a problem |


  Scenario Outline: Check the alert messages are displayed on the 'Create account' page
    Given User opens '<homePage>' page
    And User checks welcome message visibility
    When User clicks welcome message button
    And User clicks create account button
    And User puts incorrect '<email>' in the 'Mobile number or email' field
    And User puts incorrect '<password>' in the field 'Password'
    And User puts invalid '<passwordConfirm>' in the 'Re-enter password' field
    And User clicks continue button
    Then User checks visibility of the alert message about empty 'Your name' field
    And User checks visibility of the alert message about invalid email or mobile number
    And User checks visibility of the alert message about invalid password
    And User checks visibility of the alert message about invalid password confirmation

    Examples:
      | homePage               | email         | password | passwordConfirm|
      |https://www.amazon.com  | jlkjhlkjhasa  | 123      | 123456         |


  Scenario Outline: Check the 'Authentication required' page is shown
    Given User opens '<homePage>' page
    And User checks welcome message visibility
    When User clicks welcome message button
    And User clicks create account button
    And User puts correct '<name>' in the 'Your name' field
    And User puts correct '<email>' in the 'Mobile number or email' field
    And User puts correct '<password>' in the field 'Password'
    And User puts the same '<password>' in the 'Re-enter password' field
    And User clicks continue button
    Then User checks 'Authentication required' '<page>' is shown


    Examples:
      | homePage              | name  | email                   | password    | page                     |
      |https://www.amazon.com | Nana  | tania.adamus@dot.com    | 123456      | Authentication required  |


  Scenario Outline: Check product filtration by brand
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User chooses brand filter using <brandsListIndex>
    Then User check that current URL contains '<keyword>'
    And User check that current URL contains  brand name

    Examples:
      | homePage               | keyword       | brandsListIndex |
      |https://www.amazon.com  | laptop        | 2               |


  Scenario Outline: Add product to the cart
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User chooses the first listed product
    And User adds product to the cart
    And User clicks 'Cart' item
    Then User checks the product is in the cart



    Examples:
      | homePage               | keyword    |
      |https://www.amazon.com  | headphones |



  Scenario Outline: Add some similar products to the cart
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User chooses the first listed product
    And User selects '<quantity>' of products in the drop-down list
    And User adds product to the cart
    And User clicks 'Cart' item
    Then User checks total '<quantity>' of products in the cart


    Examples:
      | homePage               | keyword    | quantity |
      |https://www.amazon.com  | headphones | 10       |


  Scenario Outline: check the total summ in cart is correct
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    And User chooses the first listed product
    And User adds to cart related products
    And User clicks 'Cart' item
    Then User checks total sum in the cart


    Examples:
      | homePage               | keyword    |
      | https://www.amazon.com | notebook   |


  Scenario Outline: Check URL contains keyword
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyword>'
    Then User check that current URL contains '<keyword>'


    Examples:
      | homePage               | keyword    |
      | https://www.amazon.com | notebook   |


  Scenario Outline: Check sign-in as registered user
    Given User opens '<homePage>' page
    And User checks welcome message visibility
    When User clicks welcome message button
    And User inputs correct '<email>' in the 'Sigh-In' field
    And User inputs correct '<password>' in the 'Password' field
    And User clicks welcome message button
    Then User checks '<accountPage>' is shown




    Examples:
      | homePage              | email                    | password    | accountPage |
      |https://www.amazon.com | tania.adamus@gmail.com   | 123456      | Your Account|