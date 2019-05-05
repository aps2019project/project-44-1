package models;

import java.util.ArrayList;

public class Account implements Comparable<Account> {

    private String username;
    private String password;
    private int money = 15000;
    private int wins = 0;
    private ArrayList<Deck> decks = new ArrayList<>();
    private Collection collection = new Collection();
    private ArrayList<MatchHistory> histories = new ArrayList<>();
    private Deck mainDeck = collection.getMainDeck();

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
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
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

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public int getWins() {
        return wins;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
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

    public void decreaseMoney(int decreasedMoney) {
        this.money -= decreasedMoney;
    }

    public boolean isReadyToPlay() {
        if (collection.getMainDeck() == null) {
            return false;
        }
        return collection.validateDeck(mainDeck.getName());
    }

}