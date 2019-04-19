package models;

import java.util.ArrayList;
import java.util.Random;

public class Account implements Comparable<Account> {

    private String username;
    private String password;
    private int money;
    private Deck mainDeck = new Deck();
    private int wins;
    private ArrayList<Deck> decks = new ArrayList<>();
    private Collection collection = new Collection();
    private Placeable[] hand = new Placeable[5];
    private static Account myAccount = new Account();

    private Account() {
    }

    public static Account getMyAccount() {
        return myAccount;
    }

    public void save() {
    }

    public static void setMyAccount(Account account) {
        myAccount = account;
    }

    public void initializeHand() {
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            int a = r.nextInt(20);
            hand[i] = mainDeck.placeables[a];
            mainDeck.placeables[a] = null;
        }
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
}
