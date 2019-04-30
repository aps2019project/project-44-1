package models;

import java.util.ArrayList;

class Map {
    private Cell[][] cells = new Cell[5][9];

    Cell[][] getCells() {
        return cells;
    }

    private ArrayList<Item> flags = new ArrayList<>();

    public ArrayList<Item> getFlags() {
        return flags;
    }

    Map(Cell... flagCell) {
        for (Cell c : flagCell) {
            Item flag = new Item();
            flag.setCell(c);
            flag.itemType = ItemType.FLAG;
            flags.add(flag);
        }
    }
}
