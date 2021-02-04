package REST.doRegister;

import REST.TestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import controller.RegisterController;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Register;
import model.SimpleModel;
import REST.model.SuccessfulRegister;
import org.testng.annotations.BeforeMethod;


public class DoRegisterTestBase implements TestBase {
    RegisterController controller = new RegisterController();

    String email;
    String name;
    String password;

    Register register;

    @BeforeMethod(description = "Generating Data")
    public void generateData() {
        email = faker.internet().emailAddress();
        name = faker.name().firstName();
        password = faker.internet().password();
    }

    @Step("Generate JSON Data")
    public void createRegisterPOST(String email, String name, String password) {
        register = new Register(email, name, password);
    }

    @Step("Send POST")
    public Response sendPOST() {
        return controller.createSimplePOST(register);
    }

    @Step("Getting RegisterResult")
    public SimpleModel getResult(Response response) throws JsonProcessingException {
        SimpleModel result;
        addAttachMessage(response);
        if (response.body().jsonPath().get("type") == null) {
            result = mapper.readValue(response.asString(), SuccessfulRegister.class);
        } else {
            result = getError(response);
        }
        return result;
    }
}
