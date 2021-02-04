package REST.model;

import model.SimpleModel;

public class Error extends SimpleModel {
    public String type;
    public String message;

    @Override
    public String toString() {
        return "{\n" +
                "\t\"type\": \"" + type + "\",\n" +
                "\t\"massage: \"" + message + "\"\n" +
                '}';
    }
}
