package ru.job4j.io.search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TypeSearch {

    private static void writeSearch(List<Path> sources, String target) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
            for (Path file : sources) {
                bw.write(file.toString());
                bw.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void  search(ArgsName argsName) throws IOException {
    if (("name").equals(argsName.get("t"))) {
        SearchFile search = new SearchFile(k -> k.toFile().getName().equals(argsName.get("n")));
        Files.walkFileTree(Path.of(argsName.get("d")), search);
        writeSearch(search.getResult(), argsName.get("o"));
    } else if (("mask").equals(argsName.get("t"))) {
        String mask = argsName.get("n").replace(".", "\\.")
                .replace("*", "\\w*").replace("?", "\\w");
        SearchFile search = new SearchFile(k -> k.getFileName().toString().matches(mask));
        Files.walkFileTree(Path.of(argsName.get("d")), search);
        writeSearch(search.getResult(), argsName.get("o"));
    } else if (("regex").equals(argsName.get("t"))) {
        String regex = argsName.get("n");
        SearchFile search = new SearchFile(k -> k.getFileName().toString().matches(regex));
        Files.walkFileTree(Path.of(argsName.get("d")), search);
        writeSearch(search.getResult(), argsName.get("o"));
    }
}
}
