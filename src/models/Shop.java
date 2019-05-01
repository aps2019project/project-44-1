package models;

import java.util.ArrayList;

public class Shop {
    private static Shop shop = new Shop();
    ArrayList<String> cardNames = new ArrayList<>();
    private ArrayList<Placeable> cards = new ArrayList<>();
    private Account account;


    private Shop() {

    }

    public static Shop getInstance() {
        return shop;
    }


    public void help() {
    }

    public void showShopCards() {
    }


    /** sell() returns true when the selling is done completely and return false in the other case*/
    public boolean sell(int cardID) {
        Placeable card = account.getCollection().getCard(cardID);
        if (card != null) {
            account.getCollection().deleteCardFromCollection(cardID);
            account.increaseMoney(card.getCost());
            return true;
        }
        return false;
    }

    public void buy(String cardName) {
    }

    public void searchInShop(String cardName) {
    }

    public void searchInCollection(String cardName) {
    }

    @Override
    public String toString() {
        return "1.show collection\n" +
                "2.search [item name | card name]\n" +
                "3.search collection [item name | card name]\n" +
                "4.buy [card name | item name]\n" +
                "5.sell [card id | card id]\n" +
                "6.show\n" +
                "7.help\n" +
                "8.exit";
    }

    public ArrayList<Placeable> getCards() {
        return cards;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}