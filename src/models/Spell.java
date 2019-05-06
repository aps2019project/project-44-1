package models;

public class Spell extends Card {

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

    String getSpellInfoInBattle() {
        return "Spell:\n" +
                "Name : " + this.getName() + "\n" +
                "MP :" + this.getNeededMana() + "\n" +
                "Cost : " + this.getCost() + "\n" +
                "Desc : ";// TODO: 06/05/2019 getdesc for hero
    }
}