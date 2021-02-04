package REST;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import REST.model.Error;
import model.SimpleModel;

public interface TestBase {
    String BASE_URL = "http://users.bugred.ru/";
    ObjectMapper mapper = new ObjectMapper();
    Faker faker = new Faker();

    default void addAttachMessage (Response response) {
        Allure.addAttachment("Get JSON", "application/json", response.asPrettyString());
    }

    default Error getError(Response response) throws JsonProcessingException {
        return mapper.readValue(response.asString(), Error.class);
    }

    default void assertModelWithClass(SimpleModel model) {
        assert !(model instanceof  Error) : "Error";
    }

    default void assertModelWithClassError(SimpleModel model) {
        assert model instanceof  Error : "Success";
    }
}
