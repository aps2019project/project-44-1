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

    public Player(Deck deck) {
        this.deck = deck;
        initializeHand();
        Collections.shuffle(deck.getDeckCards());
    }

    public Placeable[] getHand() {
        return hand;
    }

    public int getTurnsFlagSaved() {
        return turnsFlagSaved;
    }

    public int getFlagsCaptured() {
        return flagsCaptured;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Deck getDeck() {
        return deck;
    }

    private void initializeHand() {
        Random r = new Random();
        Iterator<Minion> iterator = deck.getMinions().iterator();
        for (int i = 0; i < 5; i++) {
            int a = r.nextInt(20);
            hand[i] = deck.getMinions().get(a);
            while (iterator.hasNext()) {
                if (iterator.next().equals(hand[i]))
                    iterator.remove();
            }
        }
    }

}
