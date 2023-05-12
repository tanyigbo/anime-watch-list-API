package definitions;

import com.example.AnimeAPI.AnimeApiApplication;
import com.example.AnimeAPI.enums.UserType;
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

import static com.example.AnimeAPI.enums.UserType.GENERAL;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AnimeApiApplication.class)
public class SpringBootTestDefinitions {

    private static final String BASE_URL = "http://localhost:";
    private static final RequestSpecification request  = RestAssured.given();
    private static Response response;

    @LocalServerPort
    String port;

    public SpringBootTestDefinitions() {
        RestAssured.baseURI = BASE_URL;
    }

    // Scenario: A new user is able to register and log in
    @Given("A list of users are available")
    public void aListOfUsersAreAvailable() {
        response = request.get(BASE_URL + port + "/auth/users");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> users = response.jsonPath().get("data");
        Assert.assertEquals("success",message);
        Assert.assertTrue(users.size()>0);
    }

    @When("A user registers with unique username and a password")
    public void aUserRegistersWithUniqueUsernameAndAPassword() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username","JohnDoe");
        requestBody.put("password","12345");
        requestBody.put("userType","GENERAL");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL+port+"/auth/users/register");
    }

    @Then("A new user account is created and returned")
    public void aNewUserAccountIsCreatedAndReturned() {
        Assert.assertEquals(201,response.getStatusCode());
        String message = response.jsonPath().get("message");
        Map<String, String> user = response.jsonPath().get("data");
        Assert.assertEquals("user created",message);
        Assert.assertEquals("JohnDoe",user.get("username"));
        Assert.assertEquals("GENERAL",user.get("userType"));
    }

    @When("A registered user enters username and password")
    public void aRegisteredUserEntersUsernameAndPassword() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username","Username2");
        requestBody.put("password","password2");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL+port+"/auth/users/login");
//        Assert.assertEquals(200,response.getStatusCode());
    }

    // Scenario: An admin can get, add, remove, and update anime
    @Given("A list of animes are available")
    public void aListOfAnimesAreAvailable(){
        response = request.get(BASE_URL + port + "/api/animes");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> animes = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(animes.size() > 0);
    }

    @When("an admin add an anime")
    public void anAdminAddAnAnime() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "title1");
        requestBody.put("description", "description1");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL+port+"/api/animes");
    }

    @Then("the anime is added to anime model")
    public void theAnimeIsAddedToAnimeModel() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("an admin remove an anime")
    public void anAdminRemoveAnAnime() {
        request.header("Content-Type", "application/json");
        response = request.delete(BASE_URL + port + "/api/animes/1");
    }

    @Then("the anime is removed from anime model")
    public void theAnimeIsRemovedFromAnimeModel() {
        Assert.assertEquals(204, response.getStatusCode());
    }

    @When("an admin update an anime")
    public void anAdminUpdateAnAnime() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Naruto");
        requestBody.put("description", "Ninjas at war");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/animes/2");
    }

    @Then("the anime is updated in anime model")
    public void theAnimeIsUpdatedInAnimeModel() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("an admin search anime by id")
    public void anAdminSearchAnimeById() {
        request.header("Content-Type", "application/json");
        response = request.get(BASE_URL + port + "/api/animes/2");

    }

    @Then("that anime is returned from anime model")
    public void thatAnimeIsReturnedFromAnimeModel() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    // Scenario: An admin can get, add, remove, and update genre
    @Given("A list of genres are available")
    public void aListOfGenresAreAvailable() {
        response = request.get(BASE_URL + port + "/api/genres");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> genres = response.jsonPath().get("data");
        Assert.assertEquals("success",message);
        Assert.assertTrue(genres.size()>0);
    }

    @When("an admin add a genre")
    public void anAdminAddAGenre() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "name1");
        requestBody.put("description", "description1");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/genres");
    }

    @Then("the genre is added to genre model")
    public void theGenreIsAddedToGenreModel() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("an admin remove a genre")
    public void anAdminRemoveAGenre() {
        request.header("Content-Type", "application/json");
        response = request.delete(BASE_URL + port + "/api/genres/1");
    }

    @Then("the genre is removed from genre model")
    public void theGenreIsRemovedFromGenreModel() {
        Assert.assertEquals(204, response.getStatusCode());
    }

    @When("an admin update a genre")
    public void anAdminUpdateAGenre() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Shonen");
        requestBody.put("description", "Martial arts.");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/genres/2");
    }

    @Then("the genre is updated in genre model")
    public void theGenreIsUpdatedInGenreModel() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("an admin search genre by id")
    public void anAdminSearchGenreById() {
        request.header("Content-Type", "application/json");
        response = request.get(BASE_URL + port + "/api/genres/2");
    }

    @Then("that genre is returned from genre model")
    public void thatGenreIsReturnedFromGenreModel() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    // Scenario: Any logged-in user can add anime to their watchlist
    @Given("a list of anime exists")
    public void aListOfAnimeExists() {
        response = request.get(BASE_URL + port + "/api/animes");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> animes = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(animes.size() > 0);
    }

    @When("user adds anime to watchlist")
    public void userAddsAnimeToWatchlist() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("rating", "10");
        requestBody.put("watchStatus", "COMPLETED");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/animes/1");
    }

    @Then("the anime is added to user watchlist")
    public void theAnimeIsAddedToUserWatchlist() {
        Assert.assertEquals(201, response.getStatusCode());
    }


    @Given("a list of anime exist")
    public void aListOfAnimeExist() {
        response = request.get(BASE_URL + port + "/api/animes");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> animes = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(animes.size() > 0);

    }

    @And("a list of genre exists")
    public void aListOfGenreExists() {
        response = request.get(BASE_URL + port + "/api/genres");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> genres = response.jsonPath().get("data");
        Assert.assertEquals("success",message);
        Assert.assertTrue(genres.size()>0);
    }

    @When("an admin adds a anime to a genre")
    public void anAdminAddsAAnimeToAGenre(){
        request.header("Content-Type", "application/json");
        response = request.post(BASE_URL + port + "/api/anime-details/1/1");
    }

    @Then("the amime is added to genre model")
    public void theAmimeIsAddedToGenreModel() {
        Assert.assertEquals(200, response.getStatusCode());
        String responseMessage = response.jsonPath().get("message");
        Assert.assertEquals("success", responseMessage);

    }

    @When("an admin removes an anime")
    public void anAdminRemovesAnAnime() {
        request.header("Content-Type", "application/json");
        response = request.delete(BASE_URL + port + "/api/anime-details/1/1");
    }

    @Then("the anime is removed from genre model")
    public void theAnimeIsRemovedFromGenreModel() {
        Assert.assertEquals(204, response.getStatusCode());
    }
}
