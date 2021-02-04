package REST.createCompany;

import REST.TestBase;
import REST.model.SuccessfulCompany;
import REST.model.SuccessfulRegister;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import controller.CompanyController;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.Company;
import model.Register;
import model.SimpleModel;
import org.testng.annotations.BeforeMethod;

public class CreateCompanyTestBase implements TestBase {
    CompanyController controller = new CompanyController();

    String company_name;
    String company_type;
    String email_owner;

    Company company;

    @BeforeMethod(description = "Generating Data")
    public void generateData() {
        company_name = faker.company().name();
        company_type = faker.company().suffix();
        email_owner = faker.internet().emailAddress();
    }

    @Step("Generate JSON Data")
    public void createCompanyPOST(String company_name, String company_type, String email_owner) {
        company = new Company(company_name, company_type, email_owner);
    }

    @Step("Send POST")
    public Response sendPOST() {
        return controller.createSimplePOST(company);
    }

    @Step("Getting RegisterResult")
    public SimpleModel getResult(Response response) throws JsonProcessingException {
        SimpleModel result;
        addAttachMessage(response);
        if (response.body().jsonPath().get("type").equals("success")) {
            result = mapper.readValue(response.asString(), SuccessfulCompany.class);
        } else {
            result = getError(response);
        }
        return result;
    }
}
