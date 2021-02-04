package REST.createUser;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import model.SimpleModel;
import org.testng.annotations.Test;

@Epic("Create User REST Tests")
@Feature("Tests")
public class CreateUserTest extends CreateUserTestBase {
    @Test(description = "Positive Creating User Test", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void positiveCreateUser() throws JsonProcessingException {
        createUserPOST(email, name);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClass(model);
    }

    @Test(description = "Name Unique Test", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void nameUniqueTest() throws JsonProcessingException {
        createUserPOST(email, name);
        sendPOST();

        String newEmail = faker.internet().emailAddress();
        createUserPOST(newEmail, name);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClassError(model);

    }

    @Test(description = "Email Unique Test", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void emailUniqueTest() throws JsonProcessingException {
        createUserPOST(email, name);
        sendPOST();

        String newName = faker.name().username();
        createUserPOST(email, newName);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClassError(model);
    }

    @Test(description = "Correct INN Test", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void correctINNTest() throws JsonProcessingException {
        createUserPOSTWithInn(email, name, inn);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClass(model);
    }

    @Test(description = "Short INN Test", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void shortINNTest() throws JsonProcessingException {
        String inn = super.inn.substring(0, super.inn.length() - 1);
        createUserPOSTWithInn(email, name, inn);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClassError(model);
    }

    @Test(description = "Large INN Test", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void largeINNTest() throws JsonProcessingException {
        String inn = super.inn + "3";
        createUserPOSTWithInn(email, name, inn);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClassError(model);
    }

    @Test(description = "Symbols in INN Test", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void symbolsINNTest() throws JsonProcessingException {
        String inn = super.inn.substring(0, super.inn.length() - 1) + "a";
        createUserPOSTWithInn(email, name, inn);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClassError(model);
    }
}
