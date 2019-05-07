package models;

import models.Enums.ItemType;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

public class Map {

    private Cell[][] cells = new Cell[5][9];
    private ArrayList<Item> flags = new ArrayList<>();

    Map() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setXandY(i, j);
            }
        }
    }

    ArrayList<Item> getFlags() {
        return flags;
    }

    Cell[][] getCells() {
        return cells;
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

    static int getManhatanDistance(Cell start, Cell end) {
        return Math.abs(start.getX() - end.getX() + start.getY() - end.getY());
    }

    public static int getManhatanDistance(int startX, int startY, int endX, int endY) {
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

    ArrayList<Card> getAllCardsInMap() {
        ArrayList<Card> cards = new ArrayList<>();
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 9; column++) {
                if (!cells[row][column].isFree() && cells[row][column].getCard() instanceof Hero
                        || cells[row][column].getCard() instanceof Minion) {
                    cards.add(cells[row][column].getCard());
                }
            }
        }
        return cards;
    }

    public ArrayList<Card> getPlayerCardsInMap(String playerName) {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card : this.getAllCardsInMap()) {
            if (card.getOwner().getName().equals(playerName))
                cards.add(card);
        }
        return cards;
    }

    public Placeable getCard(String cardID) {
        for (int row = 0; row < 5; row++) {
            for (int column = 0; column < 9; column++) {
                if (!cells[row][column].isFree()) {
                    if (cells[row][column].getCard() != null
                            && cells[row][column].getCard().getInGameID().equals(cardID)) {
                        return cells[row][column].getCard();
                    }
                    if (cells[row][column].getItem() != null
                            && cells[row][column].getItem().getInGameID().equals(cardID)) {
                        return cells[row][column].getItem();
                    }
                }
            }
        }
        return null;
    }

}