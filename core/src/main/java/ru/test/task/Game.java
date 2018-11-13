package ru.test.task;

import ru.test.task.dto.UserPalindromsDTO;

import java.util.List;

public interface Game {
    List<UserPalindromsDTO> getLeadersTable();

    List<UserPalindromsDTO> getLeadersTable(Integer size);

    void addPhrase(String userName, String phrase);

    void uploadFullDataUsers(List<UserPalindromsDTO> leadersTable);

    void uploadPaligonsDataUsers(List<UserPalindromsDTO> leadersTable);

    List<UserPalindromsDTO> downloadPaligonsDataUsers();

    void setLeaderTableSize(Integer leaderTableSize);
}
