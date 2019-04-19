package models;

import java.util.ArrayList;
import java.util.HashMap;

public class Collection implements Comparable<Collection> {
    private static final int maxItems = 3;
    private ArrayList<Deck> decks = new ArrayList<>();
    private HashMap<Integer,Card> cardHashMap = new HashMap<>();
    private HashMap<Integer,Item> itemHashMap = new HashMap<>();


    public void showCollection() {
    }       //TODO sort the collection and print it on view

    public void search(String cardName) {

    }

    public void save() {

    }

    public void createDeck(String deckName) {

    }

    public void deleteDeck(String deckName) {

    }

    public void addToDeck(int cardID, String deckName) {

    }

    public void removeFromDeck(int cardID, String deckName) {

    }

    public void validateDeck(String deckName) {

    }

    public void selectMainDeck(String deckName) {

    }

    public void showAllDecks() {

    }

    public void showDeck(String deckName) {

    }

    @Override
    public int compareTo(Collection collection) {
        return 0;
    }       //TODO
}