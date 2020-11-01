Feature: Account activity content

  Scenario: verify title
    Given the user is logged in
    When navigate to "Account Activity" page
    Then the title should be "Zero - Account Activity"

  Scenario: Default dropDown option
    Given the user is logged in
    When navigate to "Account Activity" page
    Then dropdown option should be "Savings"


  Scenario: dropdown options
    Given the user is logged in
    When navigate to "Account Activity" page
    Then dropdown options should be followings:
      | Savings     |
      | Checking    |
      | Savings     |
      | Loan        |
      | Credit Card |
      | Brokerage   |

  Scenario: Transactions table columns
    Given the user is logged in
    When navigate to "Account Activity" page
    Then transaction table columns are followings:
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |
