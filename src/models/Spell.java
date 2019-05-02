package models;

public class Spell extends Placeable {

    void effect(Cell... cells) {
    }

    void effect(Card... myCards) {
    }

    void effect(int... opponentCardsCoordinates) {
    }

    @Override
    public String toString() {
        return " : Type : " + this.getClass().getName() +
                " : Name : " + this.getName() +
                " - MP : " + this.getNeededMana() +
                " - Desc : ";
        // TODO: 30/04/2019 get Desc in string
    }
}