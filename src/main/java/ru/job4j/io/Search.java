package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validationArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void validationArgs(String[] args) {
        if (args.length == 0 || !args[0].contains("/")) {
            throw new IllegalArgumentException("Root folder is null or not correct. Usage java -jar Search.jar ROOT_FOLDER.");
        } else if (args.length == 1 || !args[1].contains(".")) {
            throw new IllegalArgumentException("Condition is null or not correct. Usage java -jar Search.jar CONDITION");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}