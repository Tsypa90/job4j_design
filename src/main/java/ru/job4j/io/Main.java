package ru.job4j.io;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person("Pavel", 30, true,
                new Contact(192532, "+7(921) 871-2010"), new String[]{"hockey", "cars"});
        JSONObject jsonContact = new JSONObject("{\"zipCode\":192532, \"phone\":\"+7(921) 871-2010\"}");
        JSONArray jsonInterests = new JSONArray(person.getInterests());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", person.getName());
        jsonObject.put("age", person.getAge());
        jsonObject.put("married", person.isMarried());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("interests", jsonInterests);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(person).toString());
    }
}
