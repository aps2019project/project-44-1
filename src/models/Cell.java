package models;

class Cell {
    int x;
    int y;
    boolean free = true;
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

    public Placeable getCard() {
        return card;
    }

    void effecting() {
        switch (getCellEffect()) {
            case POISION:
                decreaseHP(card, 1);
                break;
            case FIRE:
                decreaseHP(card,2);
            case HOLLY:
        }

    }

    private void decreaseHP(Card card, int amount) {
        card.HP -= amount;
    }
}
