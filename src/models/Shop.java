package models;

import java.util.ArrayList;

public class Shop {
    ArrayList<String> cardNames = new ArrayList<>();
    private ArrayList<Placeable> cards= new ArrayList<>();


    public void help() {
    }

    public void showShopCards() {
    }

    public void sell(int cardID) {

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
}