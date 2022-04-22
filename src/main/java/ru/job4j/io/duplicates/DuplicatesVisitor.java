package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private static Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toAbsolutePath().toFile().length(),
                file.toAbsolutePath().toFile().getName());
        if (duplicates.containsKey(fileProperty)) {
            duplicates.get(fileProperty).add(file.toAbsolutePath());
        } else {
            duplicates.put(fileProperty, new ArrayList<>());
            duplicates.get(fileProperty).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

    public void printPath() {
        for (List<Path> value : duplicates.values()) {
            if (value.size() > 1) {
                for (Path path : value) {
                    System.out.println(path);
                }
            }
        }
    }
}
