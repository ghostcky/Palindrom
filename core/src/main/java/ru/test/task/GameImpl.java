package ru.test.task;

import ru.test.task.dto.UserPalindromsDTO;

import java.util.*;
import java.util.stream.Collectors;

public class GameImpl implements Game {

    /**
     * Пользовательские палиндромы
     */
    private Map<String, UserPalindromsDTO> usersPalindroms = new HashMap<>();
    private List<UserPalindromsDTO> leadersTable = new ArrayList<>();
    private Integer leaderTableSize;

    public GameImpl() {
        leaderTableSize = 5;
    }

    /**
     * @param leaderTableSize Размер выборки для таблицы лидеров
     */
    public GameImpl(Integer leaderTableSize) {
        this.leaderTableSize = leaderTableSize;
    }

    /**
     * Получить таблицу пользователей
     *
     * @return Список Лидеров
     */
    @Override
    public List<UserPalindromsDTO> getLeadersTable() {
        Integer size = leadersTable.size() < leaderTableSize ? leadersTable.size() : leaderTableSize;
        return getLeadersTable(size);
    }

    /**
     * Получить таблицу пользователей
     *
     * @param size размер выгрузки
     * @return Список Лидеров
     */
    @Override
    public List<UserPalindromsDTO> getLeadersTable(Integer size) {
        if(size != null && size != 0) {

            Integer correctSize = leadersTable.size() < size ? leadersTable.size() : size;
            Collections.sort(leadersTable);

            return leadersTable.subList(0, correctSize);
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Добавление фразы
     *
     * @param userName имя пользователя
     * @param phrase   фраза
     */
    @Override
    public void addPhrase(String userName, String phrase) {

        if (!usersPalindroms.containsKey(userName)) {

            UserPalindromsDTO userData = new UserPalindromsDTO(userName);

            usersPalindroms.put(userName, userData);
            leadersTable.add(userData);
        }

        UserPalindromsDTO userPhrases = usersPalindroms.get(userName);

        if (!userPhrases.hasPalindrom(phrase))
            userPhrases.addPalindroms(phrase);

    }

    /**
     * Подгрузка полных пользовательских данных.
     *
     * @param leadersTable Заполнены palindroms, userName, coins
     */
    @Override
    public void uploadFullDataUsers(List<UserPalindromsDTO> leadersTable) {
        this.leadersTable = leadersTable;
        usersPalindroms = leadersTable.stream().collect(
                Collectors.toMap(UserPalindromsDTO::getUserName, x -> x));
    }

    /**
     * Подгрузка пользовательских данных не содержащих итоговых баллов.
     *
     * @param leadersTable Заполнены только palindroms, userName
     */
    @Override
    public void uploadPaligonsDataUsers(List<UserPalindromsDTO> leadersTable) {
        leadersTable.forEach(UserPalindromsDTO::reCalcCoins);

        this.leadersTable = leadersTable;
        usersPalindroms = leadersTable.stream().collect(
                Collectors.toMap(UserPalindromsDTO::getUserName, x -> x));
    }

    /**
     * Выгрузить все пользовательские данные
     *
     * @return пользовательские данные
     */
    @Override
    public List<UserPalindromsDTO> downloadPaligonsDataUsers() {
        return leadersTable;
    }

    /**
     * Изменить размер таблицы лидеров
     *
     * @param leaderTableSize
     */
    @Override
    public void setLeaderTableSize(Integer leaderTableSize) {
        this.leaderTableSize = leaderTableSize;
    }
}
