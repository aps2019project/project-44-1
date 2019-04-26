package models;

import view.View;

import java.util.HashMap;

public class Collection implements Comparable<Collection> {
    private static final int maxItems = 3;
    private HashMap<String, Deck> decks = new HashMap<>();
    private HashMap<Integer, Placeable> cardHashMap = new HashMap<>();
    private View view = new View();


    public void showCollection() {

    }       //TODO sort the collection and print it on view

    public void search(String cardName) {
        for (HashMap.Entry<Integer, Placeable> entry : cardHashMap.entrySet()) {
            if (entry.getValue().name.equals(cardName)) {
                view.sout(entry.getKey());
                return;
            }
        }
        view.printError(ErrorType.CARD_NOT_FOUND);
    }

    public void createDeck(String deckName) {
        if (decks.get(deckName) != null) {
            view.printError(ErrorType.DUPLICATE_DECK);
            return;
        }
        Deck deck = new Deck();
        deck.setName(deckName);
        decks.put(deckName, deck);
    }

    public void deleteDeck(String deckName) {
        if (decks.remove(deckName) == null)
            view.printError(ErrorType.DECK_NOT_FOUND);
    }

    public void addToDeck(int cardID, String deckName) {
        if (cardHashMap.get(cardID) == null) {
            view.printError(ErrorType.CARD_NOT_FOUND);
            return;
        } else if (decks.get(deckName) == null) {
            view.printError(ErrorType.DECK_NOT_FOUND);
            return;
        }
        for (Placeable p : decks.get(deckName).getPlaceables()) {
            if (p.getId() == cardID) {
                view.printError(ErrorType.DUPLICATE_CARD);
                return;
            }
        }
        if (decks.get(deckName).isFull()) {
            view.printError(ErrorType.FULL_DECK);
            return;
        }
        decks.get(deckName).addToDeck(cardHashMap.get(cardID));
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