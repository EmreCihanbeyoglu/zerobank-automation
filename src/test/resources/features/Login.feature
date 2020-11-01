@wip
Feature: Login functionality

  Scenario: Only authorized users login
    Given navigate login page
    When enter valid credentials
    Then the "Account Summary" page should be displayed

  Scenario Outline: non-authorized users cannot login
    Given navigate login page
    When enter credentials and click button
      | username | <usrnm> |
      | password | <pswrd> |
    Then Login error message "Login and/or password are wrong." should be displayed

    Examples:
      | usrnm    | pswrd    |
      | emre     | cihan    |
      | emre     | password |


