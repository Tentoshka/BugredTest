package REST.createCompany;

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

public class CreateCompanyTestBase implements TestBase {
    final String CREATE_COMPANY_URL = BASE_URL +"tasks/rest/createcompany";

    Faker faker = new Faker();
    String companyName;
    String companyType;
    String[] companyUsers = new String[1];
    String emailOwner;

    @BeforeMethod(description = "Generating Data")
    public void generateData() {
        companyName = faker.company().name();
        companyType = faker.company().suffix();
        companyUsers[0] = faker.internet().emailAddress();
        emailOwner = faker.internet().emailAddress();
    }

    @Step("Create and Send POST")
    public JsonPath createPOST(String companyName, String companyType, String[] companyUsers, String emailOwner) {
        return given().
                    contentType("application/json").body(
                            createJSONRequest(generateJSONData(companyName, companyType, companyUsers, emailOwner))
                    ).
                when().
                    post(CREATE_COMPANY_URL).
                then().
                    statusCode(200).
                extract().jsonPath();
    }

    @Step("Generate JSON Data")
    private String generateJSONData(String companyName, String companyType, String[] companyUsers, String emailOwner) {
        return  "    \"" + path.companyName + "\": \"" + companyName + "\",\n" +
                "    \"" + path.companyType + "\": \"" + companyType + "\",\n" +
                "    \"" + path.companyUsers + "\": " + Arrays.toString(companyUsers) + ",\n" +
                "    \"" + path.emailOwner + "\": \"" + emailOwner + "\"";
    }
}
