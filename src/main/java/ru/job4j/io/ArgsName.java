package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("There are no such key.");
        }
        return values.get(key);
    }

    private static void validationArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("There are no args.");
        }
        for (String arg : args) {
            if (!arg.contains("=") || !arg.startsWith("-")) {
                throw new IllegalArgumentException("There are no = sign or don't start with - .");
            }
        }
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String key = arg.substring(arg.indexOf("-") + 1, arg.indexOf("="));
            String value = arg.substring(arg.indexOf('=') + 1);
            if (key.isEmpty() || value.isEmpty()) {
                throw new IllegalArgumentException("Key or Value is empty.");
            }
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        validationArgs(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}