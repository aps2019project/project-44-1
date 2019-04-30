package models;

import view.View;

import java.util.*;

public class Collection implements Comparable<Placeable> {
    private static final int maxItems = 3;
    private HashMap<String, Deck> decks = new HashMap<>();
    private HashMap<Integer, Placeable> cardHashMap = new HashMap<>();
    private View view = new View();
    private Deck mainDeck;


    public ArrayList<Placeable> getCollectionCards() {
        ArrayList<Placeable> tempList = new ArrayList<>(cardHashMap.values());
        sortCollection(tempList);
        return tempList;
    }

    public int getCollectionID(String cardName) {
        for (HashMap.Entry<Integer, Placeable> entry : cardHashMap.entrySet()) {
            if (entry.getValue().name.equals(cardName)) {
                return entry.getValue().getID();
            }
        }
        return -1;
    }

    public boolean isInCollection(String cardName) {
        for (HashMap.Entry<Integer, Placeable> entry : cardHashMap.entrySet()) {
            if (entry.getValue().name.equals(cardName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUsedDeckName(String deckName) {
        return decks.get(deckName) != null;
    }

    public void createDeck(String deckName) {
        decks.put(deckName, new Deck(deckName));
    }

    public void deleteDeck(String deckName) {
        decks.remove(deckName);
    }

    public void addToDeck(int cardID, String deckName) {
        getDeck(deckName).addToDeck(cardHashMap.get(cardID));
    }

    public Placeable getCard(int cardID) {
        return cardHashMap.get(cardID);
    }

    public Deck getDeck(String deckName) {
        return decks.get(deckName);
    }

    public boolean canAddHero(Deck deck) {
        return !deck.isSpecifiedHero();
    }

    public boolean canAddItem(Deck deck) {
        return !deck.isSpecifiedItem();
    }

    public boolean canAddCardToDeck(Deck deck, int cardID) {
        if (!(getCard(cardID) instanceof Hero) && !(getCard(cardID) instanceof Item)) {
            return !deck.isFull();
        } else {
            return true;
        }
    }

    public void removeFromDeck(int cardID, String deckName) {
        getDeck(deckName).removeFromDeck(cardHashMap.get(cardID));
    }

    public boolean validateDeck(String deckName) {
        return getDeck(deckName).isValidated();
    }

    public void selectMainDeck(String deckName) {
        setMainDeck(this.getDeck(deckName));
    }


    public void showDeck(String deckName) {

    }

    private void sortCollection(ArrayList<Placeable> tempList) {
        Collections.sort(tempList);
    }

    @Override
    public int compareTo(Placeable o) {
        return 0;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public ArrayList<Deck> getSortedDecks() {
        Deck temp = null;
        ArrayList<Deck> sortedDecks = new ArrayList<>();
        for (Deck deck : decks.values()) {
            if (mainDeck == deck) {
                temp = deck;
                continue;
            }
            sortedDecks.add(deck);
        }
        if (temp != null)
            sortedDecks.add(0, temp);
        return sortedDecks;
    }

    @Override
    public String toString() {
        return "1.show\n" +
                "2.search [card name | item name]\n" +
                "3.create deck [deck name]\n" +
                "4.delete deck [deck nAme]\n" +
                "5.add [card id | card id | hero id] to deck [deck name]\n" +
                "6.remove [card id | card id| hero id] from deck [deck name]\n" +
                "7.validate deck [deck name]\n" +
                "8.select deck [deck name]\n" +
                "9.show all decks\n" +
                "10.show deck [deck name]\n" +
                "11.save\n" +
                "12.help\n" +
                "13.exit";
    }
}