package models;

import models.Enums.ItemType;

import java.util.ArrayList;

public class Map {

    private Cell[][] cells = new Cell[5][9];
    private ArrayList<Item> flags = new ArrayList<>();

    Map() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setXandY(i, j);
            }
        }
    }

    ArrayList<Item> getFlags() {
        return flags;
    }

    public Cell[][] getCells() {
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
                if (cell.getCard() != null && cell.getCard().getName().equals(cardName)) {
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

    ArrayList<Card> getPlayerCardsInMap(String playerName) {
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

    Cell getCell(int x, int y) {
        return cells[x - 1][y - 1];
    }

    ArrayList<Card> getEffectedCards(int x, int y, Spell spell) {
        Cell cell = cells[x - 1][y - 1];
        ArrayList<Card> effectedCards = new ArrayList<>();
        switch (spell.getTarget()) {
            case MY_HERO:
                effectedCards.add(cell.getCard());
                return effectedCards;
            case OPP_HERO:
                effectedCards.add(cell.getCard());
                return effectedCards;
            case OPP_MINION_ARROUND_MY_HERO:
                effectedCards.addAll(getAroundCards(cell));
                //remove my minions and oppHero from this array
                return effectedCards;
            case SOME_CELL:

        }
        return null;
    }

    private ArrayList<Card> getAroundCards(Cell cell) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = cell.getY() - 1; i < cell.getY() + 2; i++) {
            for (int j = cell.getX() - 1; j < cell.getY() + 2; j++) {
                if (cell.getY() == i && cell.getX() == j)
                    continue;
                if (i < 0 || i > 5 || j < 0 || j > 9)
                    continue;
                if (cell.getCard() != null) {
                    cards.add(cell.getCard());
                }
            }
        }
        return cards;
    }

}