package models;

import models.Enums.ItemType;

import java.util.ArrayList;

class Map {
    private Cell[][] cells = new Cell[5][9];


    Cell[][] getCells() {
        return cells;
    }

    private ArrayList<Item> flags = new ArrayList<>();

    ArrayList<Item> getFlags() {
        return flags;
    }

    void setFlags(Cell... flagCell) {
        for (Cell c : flagCell) {
            Item flag = new Item();
            flag.setCell(c);
            flag.itemType = ItemType.FLAG;
            flag.setNeededMana(0);
            flag.setID(flags.size() + 1);
            flag.setCost(0);
            flags.add(flag);
        }
    }

}
