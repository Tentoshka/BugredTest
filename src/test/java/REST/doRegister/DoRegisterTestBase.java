package REST.doRegister;

import REST.TestBase;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

/**
 * @author Tentoshka
 */

public class DoRegisterTestBase implements TestBase {
    final String DO_REGISTER_URL = BASE_URL + "tasks/rest/doregister";
    final String AVATAR = BASE_URL + "/tmp/default_avatar.jpg";
    final int birthday = 0;
    final String gender = "";
    final int date_start = 0;
    final String hobby = "";

    Faker faker = new Faker();
    String email;
    String name;
    String password;

    @BeforeMethod(description = "Generating Data")
    public void generateData() {
        email = faker.internet().emailAddress();
        name = faker.name().firstName();
        password = faker.internet().password();
    }

    @Step("Create and Send POST")
    public JsonPath createPOST(String email, String name, String password) {
        return given().
                    contentType("application/json").body("{\n" +
                    generateJSONData(email, name, password) + "\n" +
                    "}").
                when().
                    post(DO_REGISTER_URL).
                then().
                    statusCode(200).
                extract().jsonPath();
    }

    @Step("Generate JSON Data")
    private String generateJSONData(String email, String name, String password) {
        return  "    \"email\": \"" + email + "\",\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"password\": \"" + password + "\"";
    }
}
