package models;

import java.util.ArrayList;

public class Collection implements Comparable<Collection> {
    private static final int maxItems = 3;
    private ArrayList<Deck> decks = new ArrayList<>();
    // TODO: 11/04/2019
    //   after defining the logic of the game about spell , items, minions and heroes decide to how to create deck
    //   is it needed to have the main deck in collection ?

    public void showCollection() {

    }       //TODO sort the collection and print it on view

    public void search(String cardName) {
        // TODO: 11/04/2019
        //  is it needed to have separate function for itemName ?
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
        // maybe its need that function get an account
    }

    public void showAllDecks() {

    }

    public void showDeck(String deckName) {

    }

    public void help() {

    }


    @Override
    public int compareTo(Collection collection) {
        return 0;
    }       //TODO
}