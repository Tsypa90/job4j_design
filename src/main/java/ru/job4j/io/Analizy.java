package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader log = new BufferedReader(new FileReader(source))) {
            List<String> downTime = new ArrayList<>();
            String serverDownTime = null;
            for (String line = log.readLine(); line != null; line = log.readLine()) {
                if ((line.contains("400") || line.contains("500"))) {
                    if (serverDownTime == null) {
                        serverDownTime = line.substring(line.indexOf(' ') + 1);
                    }
                } else {
                        serverDownTime = serverDownTime + ";" + line.substring(line.indexOf(' ') + 1);
                    if (!serverDownTime.substring(0, serverDownTime.indexOf(';')).equals("null")) {
                        downTime.add(serverDownTime);
                    }
                    serverDownTime = null;
                }
            }
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
                downTime.stream().forEach(out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Analizy log = new Analizy();
        log.unavailable("server.log", "unavailable.csv");
    }
}
