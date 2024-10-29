Feature: Facebook Account Actions

  Scenario: Verify changing account settings
    Given I am logged into Facebook
    When I navigate to the account settings
    And I update my account settings
    Then I should see a confirmation message for settings update

  Scenario: Verify sending a friend request
    Given I am logged into Facebook
    When I search for the user "John Doe"
    And I send a friend request to "John Doe"
    Then I should see a message saying "Friend request sent" to "John Doe"

  Scenario: Verify accepting a friend request
    Given I am logged into Facebook
    When I go to friend requests
    And I accept the friend request from "Jane Smith"
    Then I should see "Jane Smith" in my friend list

  Scenario: Verify blocking a user
    Given I am logged into Facebook
    When I search for the user "Spammer"
    And I block the user "Spammer"
    Then I should see a confirmation message saying "You have blocked Spammer"

  Scenario: Verify search functionality
    Given I am logged into Facebook
    When I search for "Photos"
    Then I should see search results related to "Photos"

  Scenario: Verify notification click functionality
    Given I am logged into Facebook
    When I click on the latest notification
    Then I should be taken to the notification's page
