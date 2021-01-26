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

        assert jsonPath.getString(path.name).equals(name) : "Names not equals";
        assert jsonPath.getString(path.email).equals(email) : "Emails not equals";
        assert jsonPath.getString(path.avatar).equals(AVATAR) : "Incorrect avatar";
        assert jsonPath.getString(path.gender).equals(gender) : "Incorrect gender";
        assert jsonPath.getInt(path.birthday) == birthday : "Incorrect birthday";
        assert jsonPath.getInt(path.dateStart) == date_start : "Incorrect date_start";
        assert jsonPath.getString(path.hobby).equals(hobby) : "Incorrect hobby";
    }

    @Step("Asserting getting and incorrectly generated data")
    public void assertIncorrectData(JsonPath jsonPath) {
        addAttachMessage(jsonPath);
                
        assert jsonPath.getString(path.message) != null : "User register successful";
    }
}
