package ru.job4j.io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private boolean married;
    private Contact contact;
    @XmlElementWrapper(name = "interests")
    @XmlElement(name = "interest")
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
