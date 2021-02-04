package REST.createCompany;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import model.SimpleModel;
import org.testng.annotations.Test;

@Epic("Create Company REST Test")
@Feature("Tests")
public class CreateCompanyTest extends CreateCompanyTestBase {
    @Test(description = "Positive Creating Company Test", priority = 1)
    @Severity(SeverityLevel.BLOCKER)
    public void positiveCreateCompanyTest() throws JsonProcessingException {
        createCompanyPOST(company_name, company_type, email_owner);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClass(model);
    }

    @Test(description = "Negative Creating Company Test", priority = 2)
    @Severity(SeverityLevel.CRITICAL)
    public void negativeCreateCompanyTest() throws JsonProcessingException {
        createCompanyPOST(company_name, company_type, email_owner);

        Response response = sendPOST();

        SimpleModel model = getResult(response);

        assertModelWithClassError(model);
    }
}
