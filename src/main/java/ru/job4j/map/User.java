package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

//    @Override
//    public String toString() {
//        return "User{"
//                + "name='" + name + '\''
//                + ", children=" + children
//                + ", birthday=" + birthday
//                + '}';
//    }

    public static void main(String[] args) {
        User one = new User("Pavel", 1, new GregorianCalendar(1990, 9, 03));
        User two = new User("Pavel", 1, new GregorianCalendar(1990, 9, 03));
        Map<User, Object> map = new HashMap<>();
        map.put(one, new Object());
        map.put(two, new Object());
        for (User test : map.keySet()) {
            System.out.println(test.toString() + "=" + map.get(test));
        }
        int i = (16 - 1) & 2306996;
        System.out.println(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
