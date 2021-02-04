package controller;

import io.restassured.response.Response;
import model.Register;

import static io.restassured.RestAssured.given;

public class RegisterController extends BaseController {
    final String DO_REGISTER_URL = BASE_URL + "/tasks/rest/doregister";

    public Response createSimplePOST(Register register) {
        return given().
               contentType("application/json").
                    body(register.toString()).
               when().
                    post(DO_REGISTER_URL);
    }
}
