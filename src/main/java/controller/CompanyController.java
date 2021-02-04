package controller;

import io.restassured.response.Response;
import model.Company;
import model.Register;

import static io.restassured.RestAssured.given;

public class CompanyController extends BaseController {
    final String CREATE_COMPANY_URL = BASE_URL + "/tasks/rest/createcompany";

    public Response createSimplePOST(Company company) {
        return given().
               contentType("application/json").
                    body(company.toString()).
               when().
                    post(CREATE_COMPANY_URL);
    }
}
