package models;

import controller.AttackType;
import models.Enums.SpecialPowerActivation;

public class Card extends Placeable implements Fight {
    private int AP;
    int HP;
    private Spell specialPower;
    private String inGameID;
    private AttackType attackType;
    private int range;
    private SpecialPowerActivation specialPowerActivation;

    void setSpecialPowerActivation(SpecialPowerActivation specialPowerActivation) {
        this.specialPowerActivation = specialPowerActivation;
    }
    public AttackType getAttackType() {
        return attackType;
    }

    void setRange(int range) {
        this.range = range;
    }

    int getHP() {
        return HP;
    }


    void setHP(int HP) {
        this.HP = HP;
    }

    int getAP() {
        return AP;
    }

    void setAP(int AP) {
        this.AP = AP;
    }

    public Spell getSpecialPower() {
        return specialPower;
    }

    void setSpecialPower(Spell specialPower) {
        this.specialPower = specialPower;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public String getInGameID() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

    public void setInGameID(String inGameID) {
        this.inGameID = inGameID;
    }
}
