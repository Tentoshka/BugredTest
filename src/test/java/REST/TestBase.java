package REST;

import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;

public interface TestBase {
    String BASE_URL = "http://users.bugred.ru/";

    default void addAttachMessage (JsonPath jsonPath) {
        if (jsonPath.getString("message") != null)
            Allure.addAttachment("Текст ошибки", "application/json", jsonPath.getString("message"));
    }
}
