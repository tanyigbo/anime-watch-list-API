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
}
