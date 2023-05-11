Feature: Anime Watch List Rest API functionalities

  Scenario: A new user is able to register and log in
    Given A list of users are available
    When A user registers with unique username and a password
    Then A new user account is created and returned
    When A registered user enters username and password
#    Then The user is logged into the account

  Scenario: An admin can get, add, remove, and update anime
    Given A list of animes are available
    When an admin add an anime
    Then the anime is added to anime model
    When an admin remove an anime
    Then the anime is removed from anime model
    When an admin update an anime
    Then the anime is updated in anime model
    When an admin search anime by id
    Then that anime is returned from anime model

  Scenario: An admin can get, add, remove, and update genre
    Given A list of genres are available
    When an admin add a genre
    Then the genre is added to genre model
    When an admin remove a genre
    Then the genre is removed from genre model
    When an admin update a genre
    Then the genre is updated in genre model
    When an admin search genre by id
    Then that genre is returned from genre model

  Scenario: A registered user can add, update, and remove anime
    Given A list of anime available for logged in user
    When I add a new anime to my watchlist
    Then the new anime is added