package REST.doRegister;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;

/**
 * @author Tentoshka
 */
public class DoRegisterFunctions extends DoRegisterTestBase{
    @Step("Asserting getting and correctly generated data")
    public void assertCorrectData(JsonPath jsonPath) {
        addAttachMessage(jsonPath);

        assert jsonPath.getString("name").equals(name) : "Names not equals";
        assert jsonPath.getString("email").equals(email) : "Emails not equals";
        assert jsonPath.getString("avatar").equals(AVATAR) : "Incorrect avatar";
        assert jsonPath.getString("gender").equals(gender) : "Incorrect gender";
        assert jsonPath.getInt("birthday") == birthday : "Incorrect birthday";
        assert jsonPath.getInt("date_start") == date_start : "Incorrect date_start";
        assert jsonPath.getString("hobby").equals(hobby) : "Incorrect hobby";
    }

    @Step("Asserting getting and incorrectly generated data")
    public void assertIncorrectData(JsonPath jsonPath) {
        addAttachMessage(jsonPath);
                
        assert jsonPath.getString("message") != null : "User creating successful";
    }
}
