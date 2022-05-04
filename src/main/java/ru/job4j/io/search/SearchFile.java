package ru.job4j.io.search;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFile extends SimpleFileVisitor<Path> {
    private List<Path> result = new ArrayList<>();
    private Predicate<Path> condition;

    public SearchFile(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getResult() {
        return result;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            result.add(file);
        }
        return super.visitFile(file, attrs);
    }
}
