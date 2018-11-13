package ru.test.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class TestHelper {
    public static List<String> getUserList(int playersCount, Game game) {
        List<String> userNames = new ArrayList<>();
        IntStream.range(0, playersCount).forEach(i -> {
            String userName = String.valueOf(i);
            String phrase = String.valueOf(i);
            game.addPhrase(userName, phrase);
            userNames.add(userName);
        });

        Collections.reverse(userNames);
        return userNames;
    }
}
