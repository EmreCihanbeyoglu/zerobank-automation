Feature: Purchase Foreign Currency

  Scenario: Available currencies
    Given Navigate "Purchase Foreign Currency" tab
    Then following currencies should be available
      | Australia (dollar)    |
      | Canada (dollar)       |
      | Switzerland (franc)   |
      | China (yuan)          |
      | Denmark (krone)       |
      | Eurozone (euro)       |
      | Great Britain (pound) |
      | Japan (yen)           |
      | Mexico (peso)         |
      | Norway (krone)        |
      | New Zealand (dollar)  |
      | Singapore (dollar)    |


  Scenario: Error message for not selecting currency
    Given Navigate "Purchase Foreign Currency" tab
    When user tries to calculate cost without selecting a currency
    Then error message should be displayed

  Scenario: Error message for not entering value
    Given Navigate "Purchase Foreign Currency" tab
    When user tries to calculate cost without entering a value
    Then error message should be displayed