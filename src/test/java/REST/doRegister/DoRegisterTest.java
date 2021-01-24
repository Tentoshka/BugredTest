package REST.doRegister;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

/**
 * @author Tentoshka
 */

@Epic("Register REST Tests")
@Feature("Tests")
public class DoRegisterTest extends DoRegisterFunctions {

    @Test(description = "Positive Register Test", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void positiveRegisterTest() {
        JsonPath res = createPOST(email, name, password);

        assertCorrectData(res);
    }

    @Test(description = "Negative Register Test", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void incorrectMailTest() {
        JsonPath res = createPOST(name, email, password);

        assertIncorrectData(res);
    }

    @Test(description = "User Already Exist Test", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void userAlreadyExistTest() {
        createPOST(email, name, password);

        JsonPath res = createPOST(email, name, password);

        assertIncorrectData(res);
    }

    @Test(description = "Empty Password Test", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void emptyPasswordTest() {
        JsonPath res = createPOST(email, name, "");

        assertIncorrectData(res);
    }

    @Test(description = "Large Password Test", priority = 2)
    @Severity(SeverityLevel.NORMAL)
    public void largePasswordTest() {
        JsonPath res = createPOST(email, name, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed commodo, nisi id faucibus gravida, est lorem ullamcorper tellus, vel congue lectus magna id dolor. Nullam gravida a risus quis semper. Nunc felis turpis, sagittis eu lacus vel, cursus sodales dolor. Mauris ac eleifend lacus, cursus rhoncus ligula. Vestibulum et porta.");

        assertIncorrectData(res);
    }
}
