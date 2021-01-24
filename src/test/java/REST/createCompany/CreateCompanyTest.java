package REST.createCompany;

import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

/**
 * @author Tentoshka
 */

@Epic("Create Company REST Test")
@Feature("Tests")
public class CreateCompanyTest extends CreateCompanyFunctions {

    @Test(description = "Positive Creating Company Test", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void positiveCreateCompanyTest() {
        JsonPath res = createPOST(companyName, companyType, companyUsers, emailOwner);

        assertCorrectData(res);
    }

    @Test(description = "Negative Creating Company Test", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void negativeCreateCompanyTest() {
        JsonPath res = createPOST(companyName, companyType, new String[]{companyType}, emailOwner);

        assertIncorrectData(res);
    }
}
