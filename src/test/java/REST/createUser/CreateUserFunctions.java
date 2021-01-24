package REST.createUser;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;

import java.util.Arrays;

/**
 * @author Tentoshka
 */

public class CreateUserFunctions extends CreateUserTestBase {

    @Step("Asserting getting and correctly generated data")
    public void assertCorrectSimpleData(JsonPath jsonPath) {

        int[] gettingTasks = getTasks(jsonPath);

        int[] gettingCompanies = getCompanies(jsonPath);

        addAttachMessage(jsonPath);

        assert jsonPath.getString("email").equals(email) : "Emails not equals";
        assert jsonPath.getString("name").equals(name) : "Names not equals";
        assert Arrays.equals(gettingTasks, tasks) : "Tasks not equals";
        assert Arrays.equals(gettingCompanies, companies) : "Companies not equals";
    }

    @Step("Asserting getting and incorrectly generated data")
    public void assertIncorrectData(JsonPath jsonPath) {
        addAttachMessage(jsonPath);

        assert jsonPath.getString("email") == null : "User creating successful";
    }

    @Step("Getting Tasks")
    private int[] getTasks(JsonPath jsonPath) {
        int[] gettingTasks = new int[tasks.length];
        gettingTasks = getData(jsonPath, "tasks", gettingTasks.length);
        Arrays.sort(gettingTasks);
        return gettingTasks;
    }

   @Step("Getting Companies")
   private int[] getCompanies(JsonPath jsonPath) {
       int[] gettingCompanies = new int[companies.length];
       gettingCompanies = getData(jsonPath, "companies", gettingCompanies.length);
       Arrays.sort(gettingCompanies);
       return gettingCompanies;
   }

    @Step("Getting Some Array Data")
    private int[] getData(JsonPath jsonPath, String path, int length) {
        int[] result = new int[length];
        for (int i = 0; i <  result.length; i++) {
            result[i] = Integer.parseInt(jsonPath.getString(path + "[" + i + "].id"));
        }
        return result;
    }
}
