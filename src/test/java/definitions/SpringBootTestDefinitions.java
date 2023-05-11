package definitions;

import com.example.AnimeAPI.AnimeApiApplication;
import com.example.AnimeAPI.enums.UserType;
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
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Then("The user is logged into the account")
    public void theUserIsLoggedIntoTheAccount() {
        String message = response.jsonPath().get("message");
        Map<String, String> user = response.jsonPath().get("data");
        Assert.assertEquals("user logged in",message);
        Assert.assertEquals("Username2",user.get("username"));
        Assert.assertEquals("GENERAL",user.get("userType"));
    }


    @Given("A list of animes are available")
    public void aListOfAnimesAreAvailable(){
        response = request.get(BASE_URL + port + "/api/animes");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> animes = response.jsonPath().get("data");
        Assert.assertEquals("success", message);
        Assert.assertTrue(animes.size() > 0);

    }

    @When("I add an anime to my watchlist")
    public void iAddAnAnimeToMyWatchlist() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "title1");
        requestBody.put("description", "description1");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL+port+"/api/animes");

    }

    @Then("the anime is added")
    public void theAnimeIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("I remove an anime")
    public void iRemoveAnAnime() {
        request.header("Content-Type", "application/json");
        response = request.delete(BASE_URL + port + "/api/animes/1");
    }

    @Then("the anime is removed")
    public void theAnimeIsRemoved() {
        Assert.assertEquals(204, response.getStatusCode());
    }

    @When("I update an anime")
    public void iUpdateAnAnime() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Naruto");
        requestBody.put("description", "Ninjas at war");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/animes/2");
    }

    @Then("the anime is updated")
    public void theAnimeIsUpdated() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("I search anime by id")
    public void iSearchAnimeById() {
        request.header("Content-Type", "application/json");
        response = request.get(BASE_URL + port + "/api/animes/2");

    }

    @Then("that anime is returned")
    public void thatAnimeIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Given("A list of genres are available")
    public void aListOfGenresAreAvailable() {
        response = request.get(BASE_URL + port + "/api/genres");
        String message = response.jsonPath().getString("message");
        List<Map<String, String>> genres = response.jsonPath().get("data");
        Assert.assertEquals("success",message);
        Assert.assertTrue(genres.size()>0);
    }

    @When("I add an genre to my watchlist")
    public void iAddAnGenreToMyWatchlist() throws JSONException {

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "name1");
        requestBody.put("description", "description1");
        request.header("Content-Type", "application/json");
        request.header("Authorization", )
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/genres");
    }

    @Then("the genre is added")
    public void theGenreIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("I remove an genre")
    public void iRemoveAnGenre() {
        request.header("Content-Type", "application/json");
        response = request.delete(BASE_URL + port + "/api/genres/1");
    }

    @Then("the genre is removed")
    public void theGenreIsRemoved() {
        Assert.assertEquals(204, response.getStatusCode());
    }

    @When("I update an genre")
    public void iUpdateAnGenre() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Shonen");
        requestBody.put("description", "Martial arts.");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/genres/2");
    }

    @Then("the genre is updated")
    public void theGenreIsUpdated() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("I search genre by id")
    public void iSearchGenreById() {
        request.header("Content-Type", "application/json");
        response = request.get(BASE_URL + port + "/api/genres/2");
    }

    @Then("that genre is returned")
    public void thatGenreIsReturned() {
        Assert.assertEquals(200, response.getStatusCode());
    }
}
