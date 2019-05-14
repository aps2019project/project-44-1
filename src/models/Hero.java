package models;

import models.Enums.AttackType;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

public class Hero extends Card {
    private boolean isDisarmed;
    private boolean isStunned;
    private ArrayList<Buffs> buffs = new ArrayList<>();


    @Override
    public String toString() {
        return " : Name : " + this.getName() +
                " - AP : " + this.getAP() +
                " - HP : " + this.getHP() +
//                " - Class : " + this.getAttackType().getNameType() +
                " - Special power: ";
        // TODO: 30/04/2019 get special power in string
    }

    public boolean isDisarmed() {
        return isDisarmed;
    }

    public void setDisarmed(boolean disarmed) {
        isDisarmed = disarmed;
    }

    public boolean isStunned() {
        return isStunned;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }

    public void applyBuffs() {
        for (Buffs buff : buffs) {
            buff.castBuff(this);
        }
    }

    String getHeroInfoInBattle() {
        return "Hero:\n" +
                "Name : " + this.getName() + "\n" +
                "Cost : " + this.getCost() + "\n" +
                "Desc : ";// TODO: 06/05/2019 getdesc for hero
    }

    @Override
    protected Hero clone() throws CloneNotSupportedException {
        Hero newHero = new Hero();
        newHero.setOwner(this.getOwner());
        newHero.setAttackAvailable(this.isAttackAvailable());
        newHero.setDisarmed(this.isDisarmed);
        newHero.setStunned(this.isStunned);
        newHero.setHP(this.getHP());
        newHero.setAP(this.getAP());
        newHero.setInGameID(this.getInGameID());
        newHero.setName(this.getName());
        newHero.setNeededMana(this.getNeededMana());
        newHero.setAttackType(this.getAttackType());
        return newHero;
    }
}
