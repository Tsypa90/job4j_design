package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        List<Integer> columnNumber = new ArrayList<>();
        StringJoiner result = new StringJoiner(argsName.get("delimiter"));
        try (BufferedReader br = new BufferedReader(new FileReader(argsName.get("path")))) {
            try (PrintWriter out = new PrintWriter(new FileWriter(argsName.get("out")))) {
                var scanner = new Scanner(br)
                        .useDelimiter(argsName.get("delimiter"));
                var lines = scanner.nextLine().split(argsName.get("delimiter"));
                var filterLine = argsName.get("filter").split(",");
                for (int i = 0; i < lines.length; i++) {
                    for (String filter : filterLine) {
                        if (lines[i].equals(filter)) {
                            columnNumber.add(i);
                            result.add(lines[i]);
                        }
                    }
                }
                if (argsName.get("out").equals("stdout")) {
                    System.out.println(result);
                } else {
                    out.println(result);
                }
                result = new StringJoiner(argsName.get("delimiter"));
                while (scanner.hasNext()) {
                     lines = scanner.nextLine().split(argsName.get("delimiter"));
                     for (Integer number : columnNumber) {
                         result.add(lines[number]);
                     }
                     if (argsName.get("out").equals("stdout")) {
                         System.out.println(result);
                     } else {
                         out.println(result);
                     }
                     result = new StringJoiner(argsName.get("delimiter"));
                 }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArgsName validationArgs(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not all conditions.");
        }
        ArgsName jvm = ArgsName.of(args);
        if (!jvm.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Path is not csv file");
        }
        return jvm;
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = validationArgs(args);
        handle(argsName);
    }
}
