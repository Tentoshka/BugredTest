package REST.createUser;

import REST.TestBase;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeMethod;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

/**
 * @author Tentoshka
 */

public class CreateUserTestBase implements TestBase {
    final String CREATE_USER_URL = BASE_URL + "tasks/rest/createuser";

    Faker faker = new Faker();
    String email;
    String name;
    int[] tasks = {12};
    int[] companies = {36, 37};
    String inn = "123456789012";

    @BeforeMethod(description = "Generating Data")
    public void generateData() {
        email = faker.internet().emailAddress();
        name = faker.name().username();
    }

    @Step("Create and Send POST")
    public JsonPath createSimplePOST(String email, String name, int[] tasks, int[] companies) {
        return given().
                   contentType("application/json").body("{ \n" +
                   generateSimpleJSONData(email, name, tasks, companies) + "\n" +
                   "}").
               when().
                   post(CREATE_USER_URL).
               then().
                   statusCode(200).
               extract().jsonPath();
    }

    @Step("Create and Send POST with INN")
    public JsonPath createPOSTWithINN(String email, String name, int[] tasks, int[] companies, String inn) {
        return given().
                   contentType("application/json").body("{\n" +
                   generateJSONDataWithINN(email, name, tasks, companies, inn) + "\n" +
                   "}").
               when().
                   post(CREATE_USER_URL).
               then().
                   statusCode(200).
               extract().jsonPath();
    }

    @Step("Create and Send POST with Gender")
    public JsonPath createSimplePOSTWithGender(String email, String name, int[] tasks, int[] companies, String gender) {
        return given().
                contentType("application/json").body("{\n" +
                generateJSONDataWithGender(email, name, tasks, companies, gender) + "\n" +
                "}").
                when().
                post(CREATE_USER_URL).
                then().
                statusCode(200).
                extract().jsonPath();
    }

    private String generateJSONDataWithGender(String email, String name, int[] tasks, int[] companies, String gender) {
        return  generateSimpleJSONData(email, name, tasks, companies) + ",\n"
                + "    \"gender\":" + gender;
    }

    @Step("Generating Simple JSON Data")
    private String generateSimpleJSONData(String email, String name, int[] tasks, int[] companies) {
        return  "    \"email\": \"" + email + "\",\n" +
                "    \"tasks\": " + Arrays.toString(tasks) + ",\n" +
                "    \"name\": \"" + name + "\",\n" +
                "    \"companies\":" + Arrays.toString(companies);
    }

    @Step("Generating JSON Data with INN")
    private String generateJSONDataWithINN(String email, String name, int[] tasks, int[] companies, String inn) {
        return  generateSimpleJSONData(email, name, tasks, companies) + ",\n"
                + "    \"inn\":" + inn;
    }
}
