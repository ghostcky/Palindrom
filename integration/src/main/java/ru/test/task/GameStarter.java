package ru.test.task;

import ru.test.task.helper.ConsoleRender;

import static ru.test.task.helper.ConsoleRender.readPhrase;
import static ru.test.task.helper.ConsoleRender.readUserName;

public class GameStarter {

    public static void main(String[] args) {
        Game game = new GameImpl();
        while (true) {
            String userName = readUserName();
            String phrase = readPhrase();
            game.addPhrase(userName, phrase);
            ConsoleRender.printLeader(game.getLeadersTable());
        }
    }
}
