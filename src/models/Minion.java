package models;

public class Minion extends Card {
    private AttackType attackType;

    @Override
    public String toString() {
        return " : Type : " + this.getClass().getName() +
                " : Name : " + this.getName() +
                " - Class : " + this.getAttackType().getNameType() +
                " - AP : " + this.getAP() +
                " - HP : " + this.getHP() +
                " - MP : " + this.getNeededMana() +
                " - Special power: ";
        // TODO: 30/04/2019 get special power in string
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }
}
