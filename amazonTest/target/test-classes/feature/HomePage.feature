Feature: Facebook Profile Actions

  Scenario: Verify updating profile details
    Given I am logged into Facebook
    When I update my profile details
    Then I should see a confirmation message for profile update

  Scenario: Verify adding a new profile picture
    Given I am logged into Facebook
    When I add a new profile picture
    Then I should see the new profile picture on my profile

  Scenario: Verify posting a status update
    Given I am logged into Facebook
    When I post a new status with the text "Hello, world!"
    Then I should see the status "Hello, world!" on my timeline

  Scenario: Verify liking a post
    Given I am logged into Facebook
    When I like the first post on my feed
    Then I should see that the post is marked as liked

     Scenario: Verify unliking a post
       Given I am logged into Facebook
       When I unlike the first post on my feed
       Then I should see that the post is not marked as liked

     Scenario: Verify commenting on a post
       Given I am logged into Facebook
       When I comment "Nice post!" on the first post
       Then I should see the comment "Nice post!" under the post

     Scenario: Verify deleting a post
       Given I am logged into Facebook
       When I post a new status with the text "This is a test post"
       And I delete the post with text "This is a test post"
       Then I should not see the post with text "This is a test post" on my timeline


