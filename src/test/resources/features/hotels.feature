@web @regression @login
Feature: Hotel feature

 // Background:
 //   Given I am on search result screen

  @hotelSearch-1
  Scenario Outline: Verify user can only view the result by selected property class
    Given I am on default locations search result screen
    When I select property class <stars>
    Then I verify system displays only <stars> hotels on search result

    Examples:
      |stars   |
      |5 stars |
      |4 stars |
      |3 stars |

  @hotelSearch-2
  Scenario: List of all of hotel within 10 miles radius of airport or downtown
    Given I am on default locations search result screen
    Then  I verify system displays all hotels within 10 miles radius of airport
    And   I verify Hilton Hotel is within radius

