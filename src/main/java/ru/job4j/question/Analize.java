package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        int changed = 0;
        Set<User> temp = new HashSet<>(current);
        temp.removeAll(previous);
        List<Integer> list = new ArrayList<>();
        for (User value : temp) {
            list.add(value.getId());
        }
        for (User value : previous) {
            if (list.contains(value.getId())) {
                rsl.setChanged(++changed);
            }
        }
        rsl.setAdded(list.size() - changed);
        rsl.setDeleted(previous.size() + temp.size() - current.size() - changed);
        return rsl;
    }
}
