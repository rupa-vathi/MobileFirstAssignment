Feature: Facebook Login and Profile Functionality

  Scenario: Verify login with valid credentials
    Given I am on the Facebook login page
    When I enter valid email and password
    And I click Login
    Then I should be redirected to the Facebook homepage

  Scenario: Verify login with invalid password
    Given I am on the Facebook login page
    When I enter a valid email and an incorrect password
    And I click Login
    Then I should see an error message saying "The password that you've entered is incorrect."

  Scenario: Verify login with an unregistered email
      Given I am on the Facebook login page
      When I enter an unregistered email and valid password
      And I click Login
      Then I should see an error message saying "The email address you entered isn't connected to an account."

  Scenario: Verify "Forgot Password" feature
    Given I am on the Facebook login page
    When I enter a valid email and an incorrect password
    And I click Login
    When I click Forgot Password

    Scenario: Verify login with blank credentials
      Given I am on the Facebook login page
      When I leave the email and password fields blank
      And I click Login
      Then I should see an error message saying "The email address or mobile number you entered isn't connected to an account."

    Scenario: Verify successful logout
         Given I am on the Facebook login page
         When I enter valid email and password
         And I click Login
         Then I should be redirected to the Facebook homepage
         When I click on the Account dropdown menu
         And I click Log Out
         Then I should be redirected to the Facebook login page

      Scenario: Verify account is restricted after multiple unsuccessful login attempts
        Given I am on the Facebook login page
        When I attempt to login with invalid credentials 5 times
        Then I should see an error message saying "The password that you've entered is incorrect."
