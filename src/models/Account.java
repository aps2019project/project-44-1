package models;

import java.util.ArrayList;
import java.util.Random;

public class Account implements Comparable<Account> {

    private String username;
    private String password;
    private int money = 15000;
    private int wins;
    private ArrayList<Deck> decks = new ArrayList<>();
    private Collection collection = new Collection();
    private Placeable[] hand = new Placeable[5];
    ArrayList<MatchHistory> histories = new ArrayList<>();
    private int mana;
    private Deck mainDeck = collection.getMainDeck();

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void initializeHand() {
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            int a = r.nextInt(20);
            hand[i] = mainDeck.getMinions().get(a);
            mainDeck.getMinions().remove(a);
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

    String getPassword() {
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

    Deck getMainDeck() {
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

    public boolean isReadyToPlay(){
        return collection.validateDeck(mainDeck.getName());
    }
}
