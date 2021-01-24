package REST.createUser;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

/**
 * @author Tentoshka
 */

@Epic("Create User REST Tests")
@Feature("Tests")
public class CreateUserTest extends CreateUserFunctions {

    @Test(description = "Positive Creating User Test", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void positiveCreateUser() {
        JsonPath res = createSimplePOST(email, name, tasks, companies);

        assertCorrectSimpleData(res);
    }

    @Test(description = "Name Unique Test", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void nameUniqueTest() {
        createSimplePOST(email, name, tasks, companies);
        String newEmail = faker.internet().emailAddress();
        JsonPath res = createSimplePOST(newEmail, name, tasks, companies);

        assertIncorrectData(res);
    }

    @Test(description = "Email Unique Test", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void emailUniqueTest() {
        createSimplePOST(email, name, tasks, companies);
        String newName = faker.name().username();
        JsonPath res = createSimplePOST(email, newName, tasks, companies);

        assertIncorrectData(res);
    }

    @Test(description = "Correct INN Test", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void correctINNTest() {
        JsonPath res = createPOSTWithINN(email, name, tasks, companies, inn);

        assertCorrectSimpleData(res);
    }

    @Test(description = "Short INN Test", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void shortINNTest() {
        String inn = super.inn.substring(0, super.inn.length() - 1);
        JsonPath res = createPOSTWithINN(email, name, tasks, companies, inn);

        assertIncorrectData(res);
    }

    @Test(description = "Large INN Test", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void largeINNTest() {
        String inn = super.inn + "3";
        JsonPath res = createPOSTWithINN(email, name, tasks, companies, inn);

        assertIncorrectData(res);
    }

    @Test(description = "Symbols in INN Test", priority = 3)
    @Severity(SeverityLevel.NORMAL)
    public void symbolsINNTest() {
        String inn = super.inn.substring(0, super.inn.length() - 1) + "a";
        JsonPath res = createPOSTWithINN(email, name, tasks, companies, inn);

        assertIncorrectData(res);
    }

    @Test(description = "Incorrect Gender Test", priority = 4)
    @Severity(SeverityLevel.MINOR)
    public void incorrectGenderTest() {
        String gender = "P";
        JsonPath res = createSimplePOSTWithGender(email, name, tasks, companies, gender);

        assertIncorrectData(res);
    }
}
