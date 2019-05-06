package models;

import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class Player {
    private Placeable[] hand = new Placeable[5];
    private int mana;
    private Deck deck;
    private int turnsFlagSaved;
    private int flagsCaptured;
    public static final int[] turnBeginMana = {2, 3, 4, 5, 6, 7, 8, 9};
    private String name;
    private Map myMap;
    private Minion nextMinionInHand;

    public Player(Deck deck, String name) {
        this.deck = deck;
        this.name = name;
        for (Minion minion : deck.getMinions()) {
            minion.setOwner(this);
        }
        deck.getHero().setOwner(this);
        Collections.shuffle(deck.getDeckCards());
        initializeHand();
    }

    void setMyMap(Map myMap) {
        this.myMap = myMap;
    }

    public Map getMyMap() {
        return myMap;
    }

    public String getName() {
        return name;
    }

    public Placeable[] getHand() {
        return hand;
    }

    int getTurnsFlagSaved() {
        return turnsFlagSaved;
    }

    int getFlagsCaptured() {
        return flagsCaptured;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    Deck getDeck() {
        return deck;
    }

    private void initializeHand() {
        Iterator<Minion> iterator = deck.getMinions().iterator();
        int i = 0;
        while (i < 5) {
            hand[i] = iterator.next();
            i++;
            iterator.remove();
        }
        nextMinionInHand = deck.getMinions().get(5);
    }

    private String IDGenerator(String cardName) {
        return name + '_' + cardName + '_' +
                (myMap.timesCardUsed(cardName) + 1);
    }

    private void insert(String cardName, int x, int y) {        //need more edit
        for (int i = 0; i < 5; i++) {
            if (hand[i].getName().equals(cardName)) {
                IDGenerator(cardName);
                return;
            }
        }
        // TODO: 5/6/2019 error "it's not in hand"
    }

}