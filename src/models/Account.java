package models;

import java.util.ArrayList;

public class Account implements Comparable<Account> {

    private String username;
    private String password;
    private int money = 15000;
    private int wins = 0;
    private Collection collection = new Collection();
    private ArrayList<MatchHistory> histories = new ArrayList<>();

    Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addMatchHistory(MatchHistory matchHistory) {
        this.histories.add(matchHistory);
    }

    public void increaseWins() {
        wins++;
    }

    public Collection getCollection() {
        return this.collection;
    }

    public String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public int getMoney() {
        return money;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public int compareTo(Account account) {
        return this.wins - account.getWins();
    }

    public void increaseMoney(int addedMoney) {
        this.money += addedMoney;
    }

    void decreaseMoney(int decreasedMoney) {
        this.money -= decreasedMoney;
    }

    public boolean isReadyToPlay() {
        if (collection.getMainDeck() == null)
            return false;
        return collection.validateDeck(collection.getMainDeck().getName());
    }

    public boolean equals(Account obj) {
        return obj.getUsername().equals(this.getUsername());
    }

    public ArrayList<MatchHistory> getHistories() {
        return histories;
    }

}
