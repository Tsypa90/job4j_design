package ru.job4j.io;

import java.io.*;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader log = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            String serverDownTime = null;
            for (String line = log.readLine(); line != null; line = log.readLine()) {
                    if ((line.contains("400") || line.contains("500"))) {
                        if (serverDownTime == null) {
                            serverDownTime = line.substring(line.indexOf(' ') + 1);
                        }
                    } else {
                        serverDownTime = serverDownTime + ";" + line.substring(line.indexOf(' ') + 1);
                        if (!"null".equals(serverDownTime.substring(0, serverDownTime.indexOf(';')))) {
                            out.println(serverDownTime);
                        }
                        serverDownTime = null;
                    }
                }
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
