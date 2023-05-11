Feature: Anime Watch List Rest API functionalities

  Scenario: A new user is able to register and log in
    Given A username is not registered
    When A user registers with unique username and a password
    Then A new user account is created and returned
    When A registered user enters username and password
    Then The user is logged into the account and provided a token
