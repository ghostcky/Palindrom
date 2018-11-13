package ru.test.task.dto;

import java.math.BigInteger;

public abstract class UserInfoDTO {
    String userName;
    BigInteger coins = BigInteger.ZERO;

    public String getUserName() {
        return userName;
    }

    public BigInteger getCoins() {
        return coins;
    }
}
