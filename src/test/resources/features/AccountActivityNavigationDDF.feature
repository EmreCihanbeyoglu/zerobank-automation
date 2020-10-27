Feature: Navigating to specific accounts in Accounts Activity

  Scenario Outline: Account redirect "<linkName>"
    Given the user is logged in
    When the user clicks on "<linkName>" link on the Account Summary page
    Then the "Account Activity" page should be displayed
    And Account drop down should have "<linkName>" selected


    Examples:
      | linkName    |
      | Savings     |
      | Brokerage   |
      | Checking    |
      | Credit Card |
      | Loan        |



