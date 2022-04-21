package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Set<FileProperty> duplicates = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!duplicates.add(new FileProperty(file.toAbsolutePath().toFile().length(),
                file.toAbsolutePath().toFile().getName()))) {
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
