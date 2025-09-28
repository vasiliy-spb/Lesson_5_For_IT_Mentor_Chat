package dev.cheercode.dialog;

import java.util.List;
import java.util.Scanner;

public class StringDialog implements Dialog<String> {
    private final String title;
    private final String failMessage;
    private final List<String> keys;
    private final Scanner scanner;

    public StringDialog(String title, String failMessage, List<String> keys) {
        this.title = title;
        this.failMessage = failMessage;
        this.keys = keys.stream().map(String::toLowerCase).toList();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String input() {
        System.out.println(title);
        while (true) {
            String input = scanner.nextLine();
            if (keys.contains(input.toLowerCase())) {
                return input;
            }
            System.out.println(failMessage);
        }
    }
}
