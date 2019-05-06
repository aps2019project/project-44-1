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

    public int getManhatanDistance(Cell start, Cell end) {
        return Math.abs(start.getX() - end.getX() + start.getY() - end.getY());
    }

    public int getManhatanDistance(int startX, int startY, int endX, int endY) {
        return Math.abs(startX - endX + startY - endY);
    }

    int timesCardUsed(String cardName) {
        int counter = 0;
        for (Cell[] c : cells) {
            for (Cell cell : c) {
                if (cell.getCard().getName().equals(cardName)) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
