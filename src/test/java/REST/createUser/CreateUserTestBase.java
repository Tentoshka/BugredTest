package REST.createUser;

import REST.TestBase;
import REST.model.SuccessfulUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import controller.UserController;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.SimpleModel;
import model.User;
import org.testng.annotations.BeforeMethod;

public class CreateUserTestBase implements TestBase {
    UserController controller = new UserController();

    String email;
    String name;

    String inn = "123456789012";

    User user;

    @BeforeMethod(description = "Generating Data")
    public void generateData() {
        email = faker.internet().emailAddress();
        name = faker.name().username();
    }

    @Step("Generate JSON Data")
    public void createUserPOST(String email, String name) {
        user = new User(email, name);
    }

    @Step("Generate JSON Data With INN")
    public void createUserPOSTWithInn(String email, String name, String inn) {
        user = new User(email, name, inn);
    }

    @Step("Send POST")
    public Response sendPOST() {
        return controller.createSimplePOST(user);
    }

    @Step("Getting RegisterResult")
    public SimpleModel getResult(Response response) throws JsonProcessingException {
        SimpleModel result;
        addAttachMessage(response);
        if (response.body().jsonPath().get("type") == null) {
            result = mapper.readValue(response.asString(), SuccessfulUser.class);
        } else {
            result = getError(response);
        }
        return result;
    }
}
