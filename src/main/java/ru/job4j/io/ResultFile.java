package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            out.write("Результат вычесления:".getBytes());
            out.write(System.lineSeparator().getBytes());
            for (int row = 0; row < 9; row++) {
                for (int cell = 0; cell < 9; cell++) {
                    out.write(String.valueOf((row + 1) * (cell + 1)).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
