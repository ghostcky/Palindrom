package ru.test.task;

import org.junit.Assert;
import org.junit.Test;
import ru.test.task.dto.UserPalindromsDTO;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

import static ru.test.task.TestHelper.getUserList;

public class GameTest {

    @Test
    public void addPhrase() {
        String userName = "1",
                phrase = "а роза упала на лапу Азора";

        Game game = new GameImpl();
        game.addPhrase(userName, phrase);
        for (UserPalindromsDTO leader : game.getLeadersTable()) {
            Assert.assertEquals("Имя пользователя не совпадае с введенным", userName, leader.getUserName());
            Assert.assertTrue("Фраза отсутствует в общем списке", leader.getPalindroms().contains(phrase));
            Assert.assertEquals("Получено некорректное количество победных очков", BigInteger.valueOf(phrase.length()), leader.getCoins());
        }
    }

    @Test
    public void addWord() {
        String userName = "1",
                phrase = "топот";

        Game game = new GameImpl();
        game.addPhrase(userName, phrase);
        for (UserPalindromsDTO leader : game.getLeadersTable()) {
            Assert.assertEquals("Получено некорректное количество победных очков", BigInteger.valueOf(phrase.length()), leader.getCoins());
        }
    }

    @Test
    public void addNotPolindrom() {
        String userName = "1",
                phrase = "12";

        Game game = new GameImpl();
        game.addPhrase(userName, phrase);
        for (UserPalindromsDTO leader : game.getLeadersTable()) {
            Assert.assertTrue("Фраза отсутствует в общем списке", leader.getPalindroms().contains(phrase));
            Assert.assertEquals("Предполагалось получить 0 победных очков", BigInteger.ZERO, leader.getCoins());
        }
    }

    @Test
    public void usersTopByName() {
        Game game = new GameImpl();
        int playersCount = 6;
        int defaultPlayersCount = 5;

        List<String> userNames = getUserList(playersCount, game);


        Assert.assertEquals("Не верное дефолтное отображаемое количество в выборке лидеров",
                game.getLeadersTable().size(),
                defaultPlayersCount);

        int idx = 0;
        for (UserPalindromsDTO leader : game.getLeadersTable()) {
            Assert.assertEquals("Не верный индекс имени в топе", leader.getUserName(), userNames.get(idx));
            idx++;
        }
    }

    @Test
    public void usersTopByCoins() {
        Game game = new GameImpl();
        String userName = "1";
        int defaultPlayersCount = 5;
        IntStream.range(0, defaultPlayersCount).forEach(i -> game.addPhrase(String.valueOf(i), String.valueOf(i)));
        game.addPhrase(userName, "2");

        Assert.assertEquals("Не верное дефолтное отображаемое количество в выборке лидеров",
                game.getLeadersTable().get(0).getUserName(),
                userName);

    }

    @Test
    public void usersDataLoadUpload() {
        Game game = new GameImpl();
        int defaultPlayersCount = 5;
        IntStream.range(0, defaultPlayersCount).forEach(i -> game.addPhrase(String.valueOf(i), String.valueOf(i)));

        List<UserPalindromsDTO> userData = game.downloadPaligonsDataUsers();
        Assert.assertNotNull("Массив выгружаемых данных не должнен быть пустым", userData);
        Assert.assertEquals("Массив выгружаемых данных не должнен быть пустым", userData.size(), defaultPlayersCount);

        game.uploadFullDataUsers(userData);
        userData = game.downloadPaligonsDataUsers();

        Assert.assertNotNull("Массив выгружаемых данных не должнен быть пустым", userData);
        Assert.assertEquals("Массив выгружаемых данных не должнен быть пустым", userData.size(), defaultPlayersCount);

        game.uploadPaligonsDataUsers(userData);
        userData = game.downloadPaligonsDataUsers();

        Assert.assertNotNull("Массив выгружаемых данных не должнен быть пустым", userData);
        Assert.assertEquals("Массив выгружаемых данных не должнен быть пустым", userData.size(), defaultPlayersCount);

    }

    @Test
    public void usersTopSize() {
        int playersCount = 6;
        Game game = new GameImpl(playersCount);
        List<String> userNames = getUserList(playersCount, game);

        int idx = 0;
        for (UserPalindromsDTO leader : game.getLeadersTable()) {
            Assert.assertEquals("Не верный индекс имени в топе",
                    leader.getCoins(),
                    BigInteger.valueOf(userNames.get(idx).length()));
            idx++;
        }
    }
}
