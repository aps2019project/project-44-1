package models;

import models.Enums.AttackType;
import models.Enums.SpecialPowerActivation;

import java.util.ArrayList;

public class Minion extends Card {

    public Minion(String name, int neededMana, int HP, int AP, AttackType attackType,
                  int range, Spell specialPower, SpecialPowerActivation specialPowerActivation) {
        this.setName(name);
        this.setNeededMana(neededMana);
        this.setHP(HP);
        this.setAP(AP);
        this.setAttackType(attackType);
        this.setRange(range);
        this.setSpecialPower(specialPower);
        this.setSpecialPowerActivation(specialPowerActivation);
    }

    @Override
    public String toString() {
        return " : Type : " + this.getClass().getName() +
                " : Name : " + this.getName() +
                " - Class : " + super.getAttackType().getNameType() +
                " - AP : " + this.getAP() +
                " - HP : " + this.getHP() +
                " - MP : " + this.getNeededMana() +
                " - Special power: ";
        // TODO: 30/04/2019 get special power in string
    }

}
