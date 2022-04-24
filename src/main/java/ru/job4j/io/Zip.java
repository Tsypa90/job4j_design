package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                    zip.putNextEntry(new ZipEntry(file.toFile().getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toFile()))) {
                        zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        if (args.length != 3) {
            throw new IllegalArgumentException("Not all conditions.");
        }
        ArgsName jvm = ArgsName.of(args);
        if (!Files.exists(Path.of(jvm.get("d")))) {
            throw new IllegalArgumentException("Directory is not exists.");
        }
        zip.packFiles(
                Search.search(Path.of(jvm.get("d")), k -> !k.toFile().getName().endsWith(jvm.get("e"))),
                new File(jvm.get("o")));
    }
}