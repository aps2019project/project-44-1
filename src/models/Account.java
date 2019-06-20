package models;

import view.View;

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

    int getMoney() {
        return money;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public int compareTo(Account account) {
        return this.wins - account.getWins();
    }

    @Override
    public String toString() {
        return "1.enter collection\n" +
                "2.enter shop\n" +
                "3.enter battle\n" +
                "4.help\n" +
                "5.exit";
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

    public void showHistory() {
        if (histories.size() == 0)
            View.getInstance().sout("no match history found :(");
        for (MatchHistory h : histories) {
            View.getInstance().sout(h.toString());
        }
    }

}