package ru.job4j.io.search;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ValidationArgs {
    public static ArgsName validation(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not all conditions.");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!Files.exists(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException("Dir to search not correct");
        }
        if (!List.of("name", "mask", "regex").contains(argsName.get("t"))) {
            throw new IllegalArgumentException("Incorrect type of searching");
        }
        if (!(argsName.get("o")).endsWith(".txt")) {
            throw new IllegalArgumentException("Log file is incorrect");
        }
        return argsName;
    }
}
