package models;

import models.Enums.CellEffect;

public class Cell {
    /**
     * has a genius method isFree to handle whether a card
     * inserted in it or not
     */
    private int x;
    private int y;
    private Item item;
    private CellEffect cellEffect;
    private Card card;

    private CellEffect getCellEffect() {
        return cellEffect;
    }

    void setPlaceable(Card card) {
        this.card = card;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Card getCard() {
        return card;
    }

    void effecting() {
        switch (getCellEffect()) {
            case POISON:
                decreaseHP(card, 1);
                break;
            case FIRE:
                decreaseHP(card, 2);
            case HOLLY:
        }

    }

    private void decreaseHP(Card card, int amount) {
        card.setHP(card.getHP() - amount);
    }

    public int getX() {
        return x;
    }

    void setXandY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    boolean isFree() {
        return card == null;
    }

    public void setCellEffect(CellEffect cellEffect) {
        this.cellEffect = cellEffect;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
