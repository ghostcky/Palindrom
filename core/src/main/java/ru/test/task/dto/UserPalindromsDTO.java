package ru.test.task.dto;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class UserPalindromsDTO extends UserInfoDTO implements Comparable<UserPalindromsDTO> {
    private Set<String> palindroms;

    public UserPalindromsDTO(String userName) {
        this.userName = userName;
        this.palindroms = new HashSet<>();
    }

    public void addPalindroms(String phrase) {
        this.coins = coins.add(BigInteger.valueOf(palindromCoins(phrase)));
        palindroms.add(phrase);
    }

    private String getTrimPhrase(String phrase) {
        return phrase.replaceAll("\\s", "").toLowerCase();
    }

    public Boolean hasPalindrom(String phrase) {
        return palindroms.contains(phrase);
    }

    @Override
    public String toString() {
        return String.format("UserId: %s, Coins: %s", this.userName, this.coins);
    }

    @Override
    public int compareTo(UserPalindromsDTO o) {
        Comparator<UserPalindromsDTO> comparator = Comparator.comparing(d -> d.coins);
        comparator = comparator.thenComparing(d -> d.userName);
        return comparator.compare(this, o) * -1;
    }

    private Integer palindromCoins(String phrase) {
        String trimPhrase = getTrimPhrase(phrase); // Проверяется без пробелов и без учетов решистра, как в задаче
        if (isPalindrom(trimPhrase)) {
            return phrase.length(); // Возвращается длина введенной фразы с учетом пробелов и пр., как в задаче
            // phrase Max response int
        } else {
            return 0;
        }
    }

    private Boolean isPalindrom(String phrase) {
        return phrase.equals(new StringBuilder(phrase).reverse().toString());
    }

    public void reCalcCoins() {
        this.coins = BigInteger.ZERO;
        for (String phrase : this.palindroms) {
            this.coins = coins.add(BigInteger.valueOf(palindromCoins(phrase)));
        }
    }

    public Set<String> getPalindroms() {
        return palindroms;
    }
}
