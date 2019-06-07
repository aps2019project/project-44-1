package models;

import models.Enums.ItemType;

public class Item extends Placeable {
    private ItemType itemType;
    private Card carrier = new Card();
    private String inGameID;

    public ItemType getItemType() {
        return itemType;
    }

    private void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    String getInGameID() {
        return inGameID;
    }

    private void setInGameID(String inGameID) {
        this.inGameID = inGameID;
    }

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
    protected Item clone() {
        Item newItem = new Item();
        newItem.carrier = this.carrier;
        newItem.itemType = this.itemType;
        newItem.inGameID = this.inGameID;
        return newItem;
    }

    void flagInitialize(int index) {
        setName("flag");
        setItemType(ItemType.FLAG);
        setNeededMana(0);
        setCost(0);
        setID(index);
        setInGameID("flag" + index);
    }

    public void saveFlag(){

    }

}
