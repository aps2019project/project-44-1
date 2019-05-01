package models;

import view.View;

import java.util.ArrayList;

public class Deck {
    private static final int maxCardNumber = 20;
    private Item item = new Item();
    private Hero hero = new Hero();
    private ArrayList<Placeable> placeables = new ArrayList<>();
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

    public ArrayList<Placeable> getPlaceables() {
        return placeables;
    }

    public void setPlaceables(ArrayList<Placeable> placeables) {
        this.placeables = placeables;
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
        if (placeables.size() == 20) {
            return true;
        } else {
            return false;
        }
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
            placeables.add(placeable);
        }
    }

    public void removeFromDeck(Placeable placeable) {
        if (placeable instanceof Hero) {
            hero = null;
        } else if (placeable instanceof Item) {
            item = null;
        } else {
            placeables.remove(placeable);
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
        for (Placeable placeable : placeables) {
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
        cards.addAll(placeables);
        return cards;
    }


}
