package models;

import view.View;

import java.util.ArrayList;

public class Deck {
    private static final int maxCardNumber = 20;
    private Item item = new Item();
    private Hero hero = new Hero();
    private ArrayList<Minion> minions = new ArrayList<>();
    private String name;
    private View view = new View();


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

    public ArrayList<Minion> getMinions() {
        return minions;
    }

    public void setMinions(ArrayList<Minion> minions) {
        this.minions = minions;
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
        return minions.size() == 20;
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
        } else {
            minions.add((Minion) placeable);
        }
    }

    public void removeFromDeck(Placeable placeable) {
        if (placeable instanceof Hero) {
            hero = null;
        } else if (placeable instanceof Item) {
            item = null;
        } else {
            minions.remove(placeable);
        }
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
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
        for (Placeable placeable : minions) {
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
        cards.addAll(minions);
        return cards;
    }


    private ArrayList<Minion> cloner(){
        ArrayList<Minion> minions = new ArrayList<>();
        for (Minion m:this.minions) {
            try {
                minions.add((Minion) m.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return minions;
    }

    @Override
    public Deck clone() throws CloneNotSupportedException {
        Deck deck = (Deck) super.clone();
        deck.item = (Item) this.item.clone();
        deck.hero = (Hero) this.hero.clone();
        deck.minions = cloner();
        return deck;
    }
}
