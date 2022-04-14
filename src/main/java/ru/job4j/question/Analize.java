package ru.job4j.question;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        int changed = 0;
        int count = 0;
        for (User prev : previous) {
            for (User curr : current) {
                if (prev.getId() == curr.getId()) {
                    if (!prev.getName().equals(curr.getName())) {
                        rsl.setChanged(++changed);
                    }
                    count++;
                }
            }
        }
        rsl.setDeleted(previous.size() - count);
        rsl.setAdded(current.size() - count);
        return rsl;
    }
}
