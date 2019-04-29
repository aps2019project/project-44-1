package models;

public abstract class Card extends Placeable {
    int AP;
    int HP;
    Spell specialPower;


    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

}
