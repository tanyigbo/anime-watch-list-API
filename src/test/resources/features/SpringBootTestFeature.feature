Feature: Anime Watch List Rest API functionalities

  Scenario: A new user is able to register and log in
    Given A list of users are available
    When A user registers with unique username and a password
    Then A new user account is created and returned
    When A registered user enters username and password
#    Then The user is logged into the account

  Scenario: A user can add, remove, and update anime
    Given A list of animes are available
    When I add an anime to my watchlist
    Then the anime is added
    When I remove an anime
    Then the anime is removed
    When I update an anime
    Then the anime is updated