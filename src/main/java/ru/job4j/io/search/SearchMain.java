package ru.job4j.io.search;

import java.io.*;

public class SearchMain {
    public static void main(String[] args) throws IOException {
    ArgsName argsName = ValidationArgs.validation(args);
    TypeSearch.search(argsName);
    }
}
