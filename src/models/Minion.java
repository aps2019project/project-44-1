package models;

//import models.Enums.AttackType;
//import models.Enums.SpecialPowerActivation;

public class Minion extends Card {

//    public Minion(String name, int neededMana, int HP, int AP, AttackType attackType,
//                  int range, Spell specialPower, SpecialPowerActivation specialPowerActivation) {
//        this.setName(name);
//        this.setNeededMana(neededMana);
//        this.setHP(HP);
//        this.setAP(AP);
//        this.setAttackType(attackType);
//        this.setRange(range);
//      this.setSpecialPower(specialPower);
//        this.setSpecialPowerActivation(specialPowerActivation);
//    }

    @Override
    public String toString() {
        return " : Type : " + this.getClass().getName() +
                " : Name : " + this.getName() +
//                " - Class : " + this.getAttackType().getNameType() +
                " - AP : " + this.getAP() +
                " - HP : " + this.getHP() +
                " - MP : " + this.getNeededMana() +
                " - Special power: ";
        // TODO: 30/04/2019 get special power in string
    }

    String getMinionInfoInBattle() {
        return "Minion:\n" +
                "Name : " + this.getName() + "\n" +
                "HP : " + this.getHP() + "\n" + "AP : " + this.getAP() +
                "\n" + "MP : " + this.getNeededMana() + "\n" +
                "Range:" +// TODO: 06/05/2019
                "Combo-ability:" +// TODO: 06/05/2019
                "Cost : " + this.getCost() + "\n" +
                "Desc : ";// TODO: 06/05/2019 get desc for hero
    }

    @Override
    protected Minion clone() throws CloneNotSupportedException {
        Minion minion = new Minion();
        minion.setOwner(this.getOwner());
        minion.setDisarmed(this.isDisarmed());
        minion.setStuned(this.isStuned());
        minion.setHP(this.getHP());
        minion.setAP(this.getAP());
        minion.setAttackAvailable(this.isAttackAvailable());
        minion.setInGameID(this.getInGameID());
        minion.setName(this.getName());
        minion.setNeededMana(this.getNeededMana());
        return minion;
    }
}
