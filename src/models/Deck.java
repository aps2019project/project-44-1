package models;

import java.util.ArrayList;

public class Deck implements Cloneable {
    private static final int maxCardNumber = 20;
    private Item item;
    private Hero hero;
    private ArrayList<Card> cards = new ArrayList<>();
    private String name;

    public Deck(String deckName) {
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

    boolean isFull() {
        return cards.size() == maxCardNumber;
    }

    boolean isSpecifiedHero() {
        return hero != null;
    }

    boolean isSpecifiedItem() {
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

    public void removeFromDeck(String name) {
        for (Card card : cards) {
            if (card.getName().equals(name)) {
                cards.remove(card);
                break;
            }
        }
        if (hero != null && hero.getName().equals(name)){
            hero = null;
        }
        if (item != null && item.getName().equals(name))
            item = null;
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

    private boolean isInDeckCards(int cardID) {
        for (Placeable placeable : cards) {
            if (placeable.getID() == cardID) {
                return true;
            }
        }
        return false;
    }

    boolean isValidated() {
        return this.isFull() && this.isSpecifiedHero();
    }

    public ArrayList<Placeable> getDeckCards() {
        ArrayList<Placeable> cards = new ArrayList<>();
        cards.add(hero);
        cards.add(item);
        cards.addAll(this.cards);
        return cards;
    }

    @Override
    public Deck clone() {
//        Deck deck = (Deck) super.clone();
        Deck deck = new Deck(this.getName());
//        deck.item = (Item) this.item.clone();
//        deck.hero = (Hero) this.hero.clone();
//        deck.cards = cloner();
        deck.cards = cloneCards(this.cards);
        deck.hero = this.hero.clone();
        if (item != null)
            deck.item = item.clone();

        return deck;
    }

    private ArrayList<Card> cloneCards(ArrayList<Card> oldCards) {
        ArrayList<Card> newCards = new ArrayList<>();
        for (Card card : oldCards) {
            try {
                if (card instanceof Minion) {
                    newCards.add((Minion) card.clone());
                    continue;
                }
                newCards.add(card.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return newCards;
    }


}