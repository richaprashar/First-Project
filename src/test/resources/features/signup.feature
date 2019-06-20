@web @regression @signup
Feature: Verify Signup Feature

  Background:
    Given I am on home page

  @signup-1
  Scenario: Verify invalid signup
    When I enter mohammad into firstname text fields on home screen
    And I enter muntakim into lastname text fields on home screen
    And I enter 888888 into mobile number text fields on home screen
    And I enter test1234 into new password text fields on home screen
    And I click on create account button on home screen
    Then I verify invalid signup error message

  @signup-2
  Scenario: Verify gender is selected
    Then I verify gender female is selected
    And I verify gender male is selected

  @signup-3
  Scenario Outline: Verify Invalid for multiple users
    When I enter mohammad into firstname text fields on home screen
    And I enter muntakim into lastname text fields on home screen
    And I enter <username> into username text fields on home screen
    And I click on signup button on home screen
    Then I verify invalid username

    Examples:
      | username                      |
      | plainaddress                  |
      | #@%^%#$@#$@#.com              |
      | @example.com                  |
      | Joe Smith <email@example.com> |
      | Joe Smith <email@example.com> |
      | email.example.com             |
      | email@example@example.com     |
      | .email@example.com            |
      | email.@example.com            |
      | email..email@example.com      |
