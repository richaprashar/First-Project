@web @regression @messenger
Feature: Messenger Feature

  Background:
    Given I am on messenger page

  @messenger-1
  Scenario: Verify invalid login from messenger page
    When I enter abcd into username field on messenger page
    And I enter  test123 into password field on messenger page
    And I click on signUp button on messenger page
    Then I verify invalid login message on signup page