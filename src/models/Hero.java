package models;

public class Hero extends Card {
    private AttackType attackType;


    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    @Override
    public String toString() {
        return " : Name : " + this.getName() +
                " - AP : " + this.getAP() +
                " - HP : " + this.getHP() +
                " - Class : " + this.getAttackType().getNameType() +
                " - Special power: ";
        // TODO: 30/04/2019 get special power in string
    }
}
