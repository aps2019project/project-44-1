package models;

import models.Enums.CellEffect;

public class Cell {
    private int x;
    private int y;
    private boolean free = true;
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public void setCellEffect(CellEffect cellEffect) {
        this.cellEffect = cellEffect;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
