package REST.doRegister;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import model.SimpleModel;
import org.testng.annotations.Test;

@Epic("Register REST Tests")
@Feature("Tests")
public class DoRegisterTest extends DoRegisterTestBase {

    @Test(description = "Positive Register Test", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void positiveRegisterTest() throws JsonProcessingException {
        createRegisterPOST(email, name, password);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClass(model);
    }

    @Test(description = "Negative Register Test", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void incorrectMailTest() throws JsonProcessingException {
        createRegisterPOST(name, email, password);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClassError(model);
    }

    @Test(description = "User Already Exist Test", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void userAlreadyExistTest() throws JsonProcessingException {
        createRegisterPOST(email, name, password);

        sendPOST();
        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClassError(model);
    }

    @Test(description = "Empty Password Test", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void emptyPasswordTest() throws JsonProcessingException {
        createRegisterPOST(email, name, "");

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClassError(model);
    }

    @Test(description = "Large Password Test", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void largePasswordTest() throws JsonProcessingException {
        String longPsw = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed commodo, nisi id faucibus gravida, est lorem ullamcorper tellus, vel congue lectus magna id dolor. Nullam gravida a risus quis semper. Nunc felis turpis, sagittis eu lacus vel, cursus sodales dolor. Mauris ac eleifend lacus, cursus rhoncus ligula. Vestibulum et porta.";
        createRegisterPOST(email, name, longPsw);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClassError(model);
    }
}
