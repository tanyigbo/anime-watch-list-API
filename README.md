# Anime-Watch-List-API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

## Table of Contents

- [Description](#description)
- [Approach](#approach)
- [ToolsAndTech](#toolsAndtech)
- [User-Story](#user-Story)
- [Roles](#roles)
    - [Backend](#backEnd)
    - [Demo](#demo)
    - [Clone](#clone)
- [Features](#features)
    - [HurdlesAndUnsolved](#hurdlesAndunsolved)
- [Credits](#credits)

## Description

- All users will be able to view Animes and Genres
- All registered users will be able to add an anime to their watchlist and record or update a rating and watch status
- All admin users will be able to create, delete and/or update Animes or Genres

## Approach

When planning the project we came up with a few ideas for our API before eventually landing on a anime API. We choose
this topic for a few
reasons but the main technical reason was its possibility for a many-to-many relationship in the database.Then we got
started on the ERD
the ERD helped the most in the design of the models/tables allowing us to see how everything would connect. Instead of
a "direct" many-to-many relationship
we made intermediate join tables.

Then there were the user stories these stories helped to design the project as a whole and kept us within a given scope
allowing us to plan toward an MVP.
User stories also helped us to design our cucumber tests and gave us an overall direction with the project and test
driven development.

We also had a fail fast methodology instead of stalling with a endless planning stage we would plan just enough to start
coding. This allowed us to
see what worked without getting married to ideas that potentially would not work. This proved to be successful because
we had a few ideas that needed to either
be scrapped or refactored.

## ToolsAndTech

- JPA
- Cucumber
- JWT security
- H2 Database
- Postman
- Kanban board
- Git
- GitHub
- Intellij
- springboot
- junit
- Java

## User-Story

- As any user, I should be able to see all Animes, so I can see what anime exist.
- As any user, I should be able to see all Genres, so I can see what genres exist.
- As an unregistered user, I should be able to register an account so that I can save my watched animes.
- As a registered user, I should be able to log in into my account so that I can view my watched animes.
- As an admin, I should be able to add new Animes, so other users can rate them.
- As an admin, I should be able to change Animes, so anime information can be up-to-date.
- As an admin, I should be able to delete Animes, so that anime no longer exists in the database.
- As an admin, I should be able to add new Genres, so other users can see them.
- As an admin, I should be able to change Genres, so genre information can be up-to-date.
- As an admin, I should be able to delete Genres, so that genres no longer exists in the database.
- As a logged in user, I should be able to create/update anime rating and update watch status.

![ERD.](/documentation/AnimeAPI-Database-ERD.png)

## [Planning board](https://github.com/users/tanyigbo/projects/2/views/1)

![Board.](/documentation/Anime-watch-list.JPG)


## Roles

### BackEnd

- Dominique Akers - Models,Controllers,Service,seed,tests
- Tobe Anyigbo - Spring boot setup,enums,tests,security
- Trevor Hendricks - Models,Controllers,Service,seed,tests

### Clone

1. Head to the [GitHub Repo](https://github.com/tanyigbo/anime-watch-list-API)
2. Click the green code.
3. Click the copy button.
4. Go to Terminal apply the following command.

```bash
git clone "add clone text here"
```

### Installation

-[Spring Jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa/3.0.6)

-[Spring Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)

-[Spring Boot Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter/3.0.6)

-[Spring Boot Starter Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web/3.0.5)

-[PostGres Driver](https://mvnrepository.com/artifact/org.postgresql/postgresql/42.6.0)

-[Cucumber](https://cucumber.io/docs/installation/java/)

## EndPoints
# Endpoints for all users
| Request Type | URL                  | Functionality                     | Access |
|--------------|----------------------|-----------------------------------|--------|
| POST         | /auth/users/register | Request to create a new user      | Public |
| POST         | /auth/users/login    | Responsible for logging in a user | Public |
| GET          | /api/genres          | Returns all genres                | Public |
| GET          | /api/anime           | Returns all anime                 | Public |

# Endpoints for registered users
| Request Type | URL                                    | Functionality                                               | Access  |
|--------------|----------------------------------------|-------------------------------------------------------------|---------|
| POST         | /api/user-anime/{animeId}              | Add an existing anime to current logged-in user's watchlist | Private |
| PUT          | /api/user-anime/{animeId}              | Update an anime watch status or rating                      | Private |
| DELETE       | /api/user-anime/{animeId}              | Removes anime from the users watchlist                      | Private |
| GET          | /api/genres/{genreId}                  | Find genre by ID                                            | Private |
| POST         | /api/anime-details/{animeId}/{genreId} | Adds a specified anime to a specified genre                 | Private |
| DELETE       | /api/anime-details/{animeId}/{genreId} | Removes a specified anime from a specified genre            | Private |
| GET          | /api/anime/{animeId}                   | Find anime by ID                                            | Private |
# Endpoints for admin users

| Request Type | URL                                    | Functionality                               | Access  |
|--------------|----------------------------------------|---------------------------------------------|---------|
| GET          | /auth/users                            | Request for all users                       | Private |
| POST         | /api/genres/add                        | Creates a genre                             | Private |
| DELETE       | /api/genres/{genreId}                  | Delete genre                                | Private |
| POST         | /api/anime/add                         | Creates an anime                            | Private |
| DELETE       | /api/anime/{animeId}                   | Delete anime                                | Private |


## Features

- Full CRUD functionality
- User login
- User register
- Security for endpoints

## HurdlesAndUnsolved

- Adding security to the api end points
- Using enums for usertype

## Documentation Used

- [Baeldung](https://www.baeldung.com/jpa-many-to-many)
- [Stackoverflow](https://stackoverflow.com/)
- [Cucumber](https://cucumber.io/docs/installation/java/#maven)