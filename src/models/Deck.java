package models;

import view.View;

import java.util.ArrayList;

public class Deck {
    private static final int maxCardNumber = 20;
    private Item item;
    private Hero hero;
    private ArrayList<Card> cards = new ArrayList<>();
    private String name;


    Deck(String deckName) {
        this.name = deckName;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getMaxCardNumber() {
        return maxCardNumber;
    }

    public boolean isFull() {
        return cards.size() == 20;
    }

    public boolean isSpecifiedHero() {
        return hero != null;
    }

    public boolean isSpecifiedItem() {
        return item != null;
    }

    public void addToDeck(Placeable placeable) {
        if (placeable instanceof Hero) {
            setHero((Hero) placeable);
        } else if (placeable instanceof Item) {
            setItem((Item) placeable);
        } else if (placeable instanceof Minion) {
            cards.add((Minion) placeable);
        } else {
            cards.add((Spell) placeable);

        }
    }

    public void removeFromDeck(Placeable placeable) {
        if (placeable instanceof Hero) {
            hero = null;
        } else if (placeable instanceof Item) {
            item = null;
        } else {
            cards.remove(placeable);
        }
    }

    public View getView() {
        return View.getInstance();
    }

    /**
     * for checking that a deck contain a card with specific ID first use below method then use removeFromDeck() method
     */
    public boolean contains(int cardID) {
        if (getHero() != null && getHero().getID() == cardID) {
            return true;
        } else if (getItem() != null && getItem().getID() == cardID) {
            return true;
        } else return isInDeckCards(cardID);
    }

    public boolean isInDeckCards(int cardID) {
        for (Placeable placeable : cards) {
            if (placeable.getID() == cardID) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidated() {
        return this.isFull() && this.isSpecifiedHero();
    }

    public ArrayList<Placeable> getDeckCards() {
        ArrayList<Placeable> cards = new ArrayList<>();
        cards.add(hero);
        cards.add(item);
        cards.addAll(this.cards);
        return cards;
    }


    private ArrayList<Card> cloner() {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card c : this.cards) {
            try {
                cards.add(c.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return cards;
    }

    @Override
    public Deck clone() throws CloneNotSupportedException {
        Deck deck = (Deck) super.clone();
        deck.item = (Item) this.item.clone();
        deck.hero = (Hero) this.hero.clone();
        deck.cards = cloner();
        return deck;
    }
}
