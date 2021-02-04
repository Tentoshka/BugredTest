package controller;

import io.restassured.response.Response;
import model.User;

import static io.restassured.RestAssured.given;

public class UserController extends BaseController {
    final String CREATE_USER_URL = BASE_URL + "/tasks/rest/createuser";

    public Response createSimplePOST(User user) {
        return given().
               contentType("application/json").
                    body(user.toString()).
               when().
                    post(CREATE_USER_URL);
    }
}
