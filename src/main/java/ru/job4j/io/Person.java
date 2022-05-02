package ru.job4j.io;

import java.util.Arrays;

public class Person {
    private String name;
    private int age;
    private boolean married;
    private Contact contact;
    private String[] interests;

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
