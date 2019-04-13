package models;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private int money;
    private Deck mainDeck = new Deck();
    private ArrayList<Deck> decks = new ArrayList<>();
    private Collection collection = new Collection();


    public Collection getCollection() {
        return collection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}

