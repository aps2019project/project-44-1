package models;

import view.View;

import java.util.ArrayList;

class Deck {
    private static final int maxCardNumber = 20;
    private Item item = new Item();
    private Hero hero = new Hero();
    private ArrayList<Placeable> placeables = new ArrayList<>();
    private String name;
    private View view = new View();


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
        for (Placeable p : placeables) {
            if (p == null)
                return false;
        }
        return true;
    }

    public void addToDeck(Placeable placeable) {
        if (placeable instanceof Hero) {
            if (this.hero == null)
                setHero((Hero) placeable);
            else view.printError(ErrorType.HERO_SET_BEFORE);
        }
        else if (placeable instanceof Item) {
            if (this.item == null)
                setItem((Item) placeable);
            else view.printError(ErrorType.ITEM_SET_BEFORE);
        }
        else if (placeable instanceof Minion){
        }
    }
}
