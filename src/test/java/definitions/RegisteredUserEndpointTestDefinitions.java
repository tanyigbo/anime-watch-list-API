package definitions;

import com.example.AnimeAPI.AnimeApiApplication;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AnimeApiApplication.class)
public class RegisteredUserEndpointTestDefinitions {
    private static final String BASE_URL = "http://localhost:";
    private static final RequestSpecification request  = RestAssured.given();
    private static Response response;

    @LocalServerPort
    String port;

    public RegisteredUserEndpointTestDefinitions() {
        RestAssured.baseURI = BASE_URL;
    }

    @Given("User list of anime available")
    public void userListOfAnimeAvailable() {
    }
}
