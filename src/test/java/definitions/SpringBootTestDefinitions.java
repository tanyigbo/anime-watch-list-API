package definitions;

import com.example.AnimeAPI.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = User.class)
public class SpringBootTestDefinitions {

    @Given("A user information is not registered")
    public void aUserInformationIsNotRegistered() {
    }

}
