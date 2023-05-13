Feature: Anime Watch List Rest API functionalities


# All user login user stories
  Scenario: An admin is able to log in to their admin account
    When An admin provides their username and password to an account
    Then The admin is logged into their admin account and a token is provided

  Scenario: A user is able to log in to their standard account
    Given A list of registered users
    When A registered user enters username and password
    Then The user is logged into the account and provided a token

#
# Unregistered User user stories
  Scenario: An unregistered user is able to register
    Given A username is not registered
    When A user registers with unique username and a password
    Then A new user account is created and returned

  Scenario: Any user is able to view  all anime
    Given A list of anime are available
    When A user searches for all anime
    Then A list of all anime is returned

  Scenario: Any user is able to view all genres
    Given A list of genre are available
    When A user searches for all genres
    Then A list of all genres is returned

#
## Registered User user stories
  Scenario: Any logged-in user can view an anime or genre by id
    When A user searches for an anime by Id
    Then The anime with provided Id is returned
    When A user searches for an genre by Id
    Then The genre with provided Id is returned

  Scenario: Any logged-in user can add or remove an anime to their watchlist
    Given a list of anime exists
    When user adds anime to watchlist
    Then the anime is added to user watchlist

  Scenario: Any logged-in user can update watch status of an anime
    Given A an anime exists
    When A user updates the watch status
    Then The anime in their watch list is updated
    When user removes an anime form their watch list
    Then the anime is removed from the user watchlist

#
## Admin user stories
  Scenario: An admin can add and remove an anime
    Given A list of anime are available
    When an admin add an anime
    Then the anime is added to anime model
    When an admin remove an anime
    Then the anime is removed from anime model

  Scenario: An admin can add and remove a genre
    When an admin add a genre
    Then the genre is added to genre model
    When an admin remove a genre
    Then the genre is removed from genre model

  Scenario: An admin can add a anime to a genre
    Given a list of anime exist
    And a list of genre exists
    When an admin adds an anime to a genre
    Then the anime is added to genre model
    When an admin removes an anime
    Then the anime is removed from genre model
