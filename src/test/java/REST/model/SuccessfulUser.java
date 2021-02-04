package REST.model;

import model.SimpleModel;

import java.util.ArrayList;
import java.util.Map;

public class SuccessfulUser extends SimpleModel {
    public String email;
    public String name;
    public String name1;
    public String hobby;
    public String surname1;
    public String fathername1;
    public String cat;
    public String dog;
    public String parrot;
    public String cavy;
    public String hamster;
    public String squirrel;
    public String phone;
    public String adres;
    public String gender;

    public Map<String, Long> date_start;
    public Map<String, Long> date_updated;
    public Map<String, Long> birthday;
    public ArrayList<String> role;
    public Map<String, Long> date_register;

    public String date;
    public String by_user;

    public ArrayList<Map<String, String>> companies;
    public ArrayList<Map<String, String>> tasks;
}
