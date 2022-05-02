package ru.job4j.io;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

public class Person {
    private String name;
    private int age;
    private boolean married;
    private Contact contact;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isMarried() {
        return married;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getInterests() {
        return interests;
    }

    private String[] interests;

    public Person() {
    }

    public Person(String name, int age, boolean married, Contact contact, String[] interests) {
        this.name = name;
        this.age = age;
        this.married = married;
        this.contact = contact;
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", married=" + married
                + ", contact=" + contact
                + ", interests" + Arrays.toString(interests)
                + '}';
    }
}
