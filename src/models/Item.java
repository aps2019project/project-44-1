package models;

import models.Enums.ItemType;

public class Item extends Placeable {
    ItemType itemType;
    private Card carrier = new Card();

    public Card getCarrier() {
        return carrier;
    }

    public void setCarrier(Card carrier) {
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return " : Name : " + this.getName() +
                " - Desc: ";
        // TODO: 30/04/2019 get Desc in item in string
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Item item = (Item) super.clone();
        item.itemType = this.itemType;
        return item;
    }
}
