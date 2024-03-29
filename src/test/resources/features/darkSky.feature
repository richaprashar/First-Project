@web @regression @darkSky
Feature: darkSky feature

  Background:
    Given I am on darkSky home page

  @darkSky-1
  Scenario: Verify Register page without user Input
    When  I click on DarkSky API button
    And   I click on SignUp button
    And   I click on Register button
    Then  I verify i am still on Register Page

  @darkSky-2
  Scenario: Verify Current Temperature should not be greater or less than the Temperature from Daily Timeline
    Then I verify current temperature is not greater or less then temps from daily timeline

  @darkSky-3
  Scenario: Verify individual day temp timeline
    When  I expand today's timeline
    Then  I verify lowest and highest temp is displayed correctly

  @darkSky-4
  Scenario: Verify timeline is displayed in correct format
    Then  I verify timeline is displayed with two hours incremented

