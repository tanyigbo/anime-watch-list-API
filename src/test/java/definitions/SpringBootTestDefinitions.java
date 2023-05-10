package definitions;

import com.example.AnimeAPI.AnimeApiApplication;
import com.example.AnimeAPI.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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

    @Given("A user information is not registered")
    public void aUserInformationIsNotRegistered() {

    }


    @Given("A list of users is available")
    public void aListOfUsersIsAvailable() {
        response = request.get(BASE_URL + port + "/api/auth/users");
    }
}
