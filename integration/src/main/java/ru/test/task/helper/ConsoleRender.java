package ru.test.task.helper;

import ru.test.task.dto.UserPalindromsDTO;

import java.util.List;
import java.util.Scanner;

public class ConsoleRender {

    private static final Scanner in = new Scanner(System.in);

    public static void printLeader(List<UserPalindromsDTO> leaders) {
        System.out.println("\n===================");
        System.out.println("= Таблица лидеров =");
        System.out.println("===================");

        leaders.forEach(System.out::println);

        System.out.println();
    }

    public static String readUserName() {
        System.out.print("Введите ваше имя: ");
        return in.next();
    }

    public static String readPhrase() {
        System.out.print("Введите палиндром: ");
        return in.next();
    }
}
