package ru.job4j.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person("Pavel", 30, true,
                new Contact(192532, "+7(921) 871-2010"), new String[]{"hockey", "cars"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        final String personJson =
                "{"
                        + "\"name\":Pavel,"
                        + "\"age\":30,"
                        + "\"married\":true,"
                        + "\"contact\":"
                        + "{"
                        + "\"zipCode\":192532,"
                        + "\"phone\":\"+7(921)871-2010\""
                        + "},"
                        + "\"interests\":"
                        + "[\"hockey\",\"cars\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}
