package REST.createCompany;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;

import java.util.Arrays;

/**
 * @author Tentoshka
 */

public class CreateCompanyFunctions extends CreateCompanyTestBase {

    @Step("Asserting getting and correctly generated data")
    public void assertCorrectData(JsonPath jsonPath) {
        String[] gettingUsers = getUsers(jsonPath);

        addAttachMessage(jsonPath);

        assert jsonPath.getString("type").equals("success") : "Company creating failed";
        assert jsonPath.getString("company.name").equals(companyName) : "Company names not equals";
        assert jsonPath.getString("company.type").equals(companyType) : "Company types not equals";
        assert Arrays.equals(gettingUsers, companyUsers) : "Company users not equals";
    }

    @Step("Asserting getting and correctly generated data")
    public void assertIncorrectData(JsonPath jsonPath) {

        addAttachMessage(jsonPath);

        assert jsonPath.getString("type").equals("error") : "Company creating successful";
    }

    @Step("Getting Users")
    private String[] getUsers(JsonPath jsonPath) {
        String[] gettingUsers = new String[companyUsers.length];
        for (int i = 0; i <  gettingUsers.length; i++) {
            gettingUsers[i] = jsonPath.getString("company.users[" + i + "]");
        }
        return gettingUsers;
    }
}
