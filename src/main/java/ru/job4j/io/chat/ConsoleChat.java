package ru.job4j.io.chat;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        ConsoleInput input = new ConsoleInput();
        boolean chat = true;
        System.out.println("Бот: Давай поговорим?");
        while (chat) {
            String phrase = input.askStr();
            log.add("Пользователь: " + phrase);
            if (phrase.equals(STOP)) {
                while (!phrase.equals(CONTINUE)) {
                    phrase = input.askStr();
                    log.add("Пользователь: " + phrase);
                }
            } else if (phrase.equals(OUT)) {
                System.out.println("Бот: Пока!");
                chat = false;
                break;
            }
            log.add("Бот: " + (answers.get((int) (Math.random() * answers.size() - 1))));
            System.out.println(log.get(log.size() - 1));
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            br.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, Charset.forName("UTF-8"), true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./chatLog.txt", "./botAnswers.txt");
        cc.run();
    }
}
