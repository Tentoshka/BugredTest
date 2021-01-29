package REST;

import REST.Paths.Paths;
import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;

public interface TestBase {
    String BASE_URL = "http://users.bugred.ru/";

    Paths path = new Paths();

    default void addAttachMessage (JsonPath jsonPath) {
        if (jsonPath.getString("message") != null)
            Allure.addAttachment("Error message", "text/plain", jsonPath.getString("message"));
    }

    default String createJSONRequest(String JSONData) {
        return "{ \n" +
                JSONData + "\n" +
                "}";
    }
}
