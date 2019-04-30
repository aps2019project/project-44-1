package models;

public class Item extends Placeable{
    Cell cell;
    ItemType itemType;


    @Override
    public String toString() {
        return " : Name : " + this.getName() +
                " - Desc: ";
        // TODO: 30/04/2019 get Desc in item in string
    }
}
