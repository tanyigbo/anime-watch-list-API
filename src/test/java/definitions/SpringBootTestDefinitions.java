package definitions;

import com.example.AnimeAPI.AnimeApiApplication;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.Map;


@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AnimeApiApplication.class)
public class SpringBootTestDefinitions {

    private static final String BASE_URL = "http://localhost:";
    private static final RequestSpecification request = RestAssured.given();
    private static Response response;
    private static String generalUserToken;
    private static String adminUserToken;

    @LocalServerPort
    String port;

    public SpringBootTestDefinitions() {
        RestAssured.baseURI = BASE_URL;
    }


    /*
     *
     * User Login
     *
     */

    /**
     * Scenario: An admin is able to log in to their admin account
     */
    @When("An admin provides their username and password to an account")
    public void anAdminProvidesTheirUsernameAndPasswordToAnAccount() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "Username3");
        requestBody.put("password", "password3");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("The admin is logged into their admin account and a token is provided")
    public void theAdminIsLoggedIntoTheirAdminAccountAndATokenIsProvided() {
        String message = response.jsonPath().get("message");
        String token = response.jsonPath().get("data");
        Assert.assertEquals("user logged in", message);
        Assert.assertEquals(String.class, token.getClass());
        adminUserToken = token;
    }


    /**
     * Scenario: A user is able to log in to their standard account
     */
    @Given("A list of registered users")
    public void aListOfRegisteredUsers() {
        request.header("Authorization", "Bearer " + adminUserToken);
        response = request.get(BASE_URL + port + "/auth/users");
        Assert.assertEquals(302, response.getStatusCode());
        List<Map<String, String>> users = response.jsonPath().get("data");
        Assert.assertTrue(users.size() > 0);
    }

    @When("A registered user enters username and password")
    public void aRegisteredUserEntersUsernameAndPassword() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "Username2");
        requestBody.put("password", "password2");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("The user is logged into the account and provided a token")
    public void theUserIsLoggedIntoTheAccountAndProvidedAToken() {
        String message = response.jsonPath().get("message");
        String token = response.jsonPath().get("data");
        Assert.assertEquals("user logged in", message);
        Assert.assertEquals(String.class, token.getClass());
        generalUserToken = token;
    }


    /*
     *
     * All User
     *
     */

    /**
     * Scenario: An unregistered user is able to register
     */
    @Given("A username is not registered")
    public void aUsernameIsNotRegistered() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "JohnDoe");
        requestBody.put("password", "12345");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login");
        Assert.assertEquals(418, response.getStatusCode());
    }

    @When("A user registers with unique username and a password")
    public void aUserRegistersWithUniqueUsernameAndAPassword() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "JohnDoe");
        requestBody.put("password", "12345");
        requestBody.put("userType", "GENERAL");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/register");
    }

    @Then("A new user account is created and returned")
    public void aNewUserAccountIsCreatedAndReturned() {
        Assert.assertEquals(201, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, String> user = response.jsonPath().get("data");
        Assert.assertEquals("user created", message);
        Assert.assertEquals("JohnDoe", user.get("username"));
        Assert.assertEquals("GENERAL", user.get("userType"));
    }

    /**
     * Scenario: Any user is able to view all anime
     */
    @Given("A list of anime are available")
    public void aListOfAnimeAreAvailable() {
        response = request.get(BASE_URL + port + "/api/anime");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> anime = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(anime.size() > 0);
    }

    @When("A user searches for all anime")
    public void aUserSearchesForAllAnime() {
        response = request.get(BASE_URL + port + "/api/anime");
    }

    @Then("A list of all anime is returned")
    public void aListOfAllAnimeIsReturned() {
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> anime = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(anime.size() > 0);
    }

    /**
     * Scenario: Any user is able to view all genres
     */
    @Given("A list of genre are available")
    public void aListOfGenreAreAvailable() {

    }

    @When("A user searches for all genres")
    public void aUserSearchesForAllGenres() {
        response = request.get(BASE_URL + port + "/api/genres");
    }

    @Then("A list of all genres is returned")
    public void aListOfAllGenresIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
        List<Map<String, String>> genres = response.jsonPath().get("data");
        String message = response.jsonPath().get("message");
        Assert.assertEquals("genres found", message);
        Assert.assertTrue(genres.size() > 0);
    }


    /*
     *
     * Registered Users
     *
     */

    /**
     * Scenario: Any logged-in user can view an anime or genre by id
     */
    @When("A user searches for an anime by Id")
    public void aUserSearchesForAnAnimeById() {
        request.header("Authorization", "Bearer " + generalUserToken);
        response = request.get(BASE_URL + port + "/api/anime/2");
    }

    @Then("The anime with provided Id is returned")
    public void theAnimeWithProvidedIdIsReturned() {
        Assert.assertEquals(302, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, String> anime = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertEquals(2, anime.get("id"));
        Assert.assertEquals("DBZ2", anime.get("title"));
        Assert.assertEquals("Monkey fights ugly aliens part 2", anime.get("description"));
    }

    @When("A user searches for an genre by Id")
    public void aUserSearchesForAnGenreById() {
        request.header("Authorization", "Bearer " + generalUserToken);
        response = request.get(BASE_URL + port + "/api/genres/2");
    }

    @Then("The genre with provided Id is returned")
    public void theGenreWithProvidedIdIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, String> genre = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertEquals(2, genre.get("id"));
        Assert.assertEquals("Adventure", genre.get("name"));
        Assert.assertEquals("Explore places", genre.get("description"));
    }

    /**
     * Scenario: Any logged-in user can add or remove an anime to their watchlist
     */
    @Given("a list of anime exists")
    public void aListOfAnimeExists() {
        // Do we need to repeat this?
    }

    @When("user adds anime to watchlist")
    public void userAddsAnimeToWatchlist() {
        request.header("Authorization", "Bearer " + generalUserToken);
        response = request.post(BASE_URL + port + "/api/user-anime/5");
    }

    @Then("the anime is added to user watchlist")
    public void theAnimeIsAddedToUserWatchlist() {
        Assert.assertEquals(201, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, Object> userAnime = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(userAnime.get("anime").toString().contains("DBZ5"));
    }

    @When("user removes an anime form their watch list")
    public void userRemovesAnAnimeFormTheirWatchList() {
        request.header("Authorization", "Bearer " + generalUserToken);
        response = request.delete(BASE_URL + port + "/api/user-anime/5");
    }

    @Then("the anime is removed from the user watchlist")
    public void theAnimeIsRemovedFromTheUserWatchlist() {
        Assert.assertEquals(200, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, Object> userAnime = response.jsonPath().get("data");
        Assert.assertEquals("delete success", message);
        Assert.assertTrue(userAnime.get("anime").toString().contains("DBZ5"));
    }

    /**
     * Scenario: Any logged-in user can update watch status of an anime
     */
    @Given("A an anime exists")
    public void aAnAnimeExists() {
        request.header("Authorization", "Bearer " + generalUserToken);
        response = request.get(BASE_URL + port + "/api/anime/5");
        Assert.assertEquals(302, response.getStatusCode());
    }

    @When("A user updates the watch status")
    public void aUserUpdatesTheWatchStatus() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("rating", 5);
        requestBody.put("watchStatus", "DROPPED");
        request.header("Authorization", "Bearer " + generalUserToken);
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/user-anime/5");
    }

    @Then("The anime in their watch list is updated")
    public void theAnimeInTheirWatchListIsUpdated() {
        Assert.assertEquals(200, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, Object> userAnime = response.jsonPath().get("data");
        Assert.assertEquals("update success", message);
        Assert.assertTrue(userAnime.get("anime").toString().contains("DBZ5"));
        Assert.assertEquals(5, userAnime.get("rating"));
        Assert.assertEquals("dropped", userAnime.get("watchStatus"));
    }

    /*
     *
     * Admin User Stories
     *
     */

    /**
     * Scenario: An admin can add and remove an anime
     */
    @When("an admin add an anime")
    public void anAdminAddAnAnime() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "title1");
        requestBody.put("description", "description1");
        request.header("Authorization", "Bearer " + adminUserToken);
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/anime/add");
    }

    @Then("the anime is added to anime model")
    public void theAnimeIsAddedToAnimeModel() {
        Assert.assertEquals(201, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, String> anime = response.jsonPath().get("data");
        Assert.assertEquals("created anime", message);
        Assert.assertEquals("title1", anime.get("title"));
        Assert.assertEquals("description1", anime.get("description"));
    }

    @When("an admin remove an anime")
    public void anAdminRemoveAnAnime() {
        request.header("Authorization", "Bearer " + adminUserToken);
        request.header("Content-Type", "application/json");
        response = request.delete(BASE_URL + port + "/api/anime/1");
    }

    @Then("the anime is removed from anime model")
    public void theAnimeIsRemovedFromAnimeModel() {
        Assert.assertEquals(204, response.getStatusCode());
    }


    /**
     * Scenario: An admin can add and remove a genre
     */
    @When("an admin add a genre")
    public void anAdminAddAGenre() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "name1");
        requestBody.put("description", "description1");
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + adminUserToken);
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/genres/add");
    }

    @Then("the genre is added to genre model")
    public void theGenreIsAddedToGenreModel() {
        Assert.assertEquals(201, response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, String> genre = response.jsonPath().get("data");
        Assert.assertEquals("created genre", message);
        Assert.assertEquals("name1", genre.get("name"));
        Assert.assertEquals("description1", genre.get("description"));
    }

    @When("an admin remove a genre")
    public void anAdminRemoveAGenre() {
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + adminUserToken);
        response = request.delete(BASE_URL + port + "/api/genres/1");
    }

    @Then("the genre is removed from genre model")
    public void theGenreIsRemovedFromGenreModel() {
        Assert.assertEquals(204, response.getStatusCode());
    }
    /**
     * Scenario: An admin can add and remove an anime for a genre
     */
    @Given("a list of anime exist")
    public void aListOfAnimeExist() {
        response = request.get(BASE_URL + port + "/api/anime");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> anime = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(anime.size() > 0);
    }

    @And("a list of genre exists")
    public void aListOfGenreExists() {
        response = request.get(BASE_URL + port + "/api/genres");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> genres = response.jsonPath().get("data");
        Assert.assertEquals("genres found", message);
        Assert.assertTrue(genres.size() > 0);
    }

    @When("an admin adds an anime to a genre")
    public void anAdminAddsAnAnimeToAGenre() {
        request.header("Authorization", "Bearer " + adminUserToken);
        request.header("Content-Type", "application/json");
        response = request.post(BASE_URL + port + "/api/anime-details/5/2");
    }

    @Then("the anime is added to genre model")
    public void theAnimeIsAddedToGenreModel() {
        Assert.assertEquals(200, response.getStatusCode());
        String responseMessage = response.jsonPath().get("message");
        Assert.assertEquals("success", responseMessage);
    }

    @When("an admin removes an anime")
    public void anAdminRemovesAnAnime() {
        request.header("Authorization", "Bearer " + adminUserToken);
        response = request.delete(BASE_URL + port + "/api/anime-details/5/2");
    }

    @Then("the anime is removed from genre model")
    public void theAnimeIsRemovedFromGenreModel() {
        Assert.assertEquals(200, response.getStatusCode());
    }
}

