package models;

import models.Enums.AttackType;
import models.Enums.SpecialPowerActivation;

public class Card extends Placeable implements Fight {
    private int AP;
    private int HP;
    private String specialPower;
    private String inGameID;
    private AttackType attackType;
    private int range;
    private SpecialPowerActivation specialPowerActivation;
    private Player owner;

    void setOwner(Player owner) {
        this.owner = owner;
    }

    void setSpecialPowerActivation(SpecialPowerActivation specialPowerActivation) {
        this.specialPowerActivation = specialPowerActivation;
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAP() {
        return AP;
    }

    void setAP(int AP) {
        this.AP = AP;
    }

//    public Spell getSpecialPower() {
//        return specialPower;
//    }
//
//    void setSpecialPower(Spell specialPower) {
//        this.specialPower = specialPower;
//    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public String getInGameID() {
        return inGameID;
    }

    public void setInGameID(String inGameID) {
        this.inGameID = inGameID;
    }

    @Override
    protected Card clone() throws CloneNotSupportedException {
        Card card = (Card) super.clone();
        card.attackType = this.attackType;
        card.specialPower = this.specialPower;
        card.specialPowerActivation = this.specialPowerActivation;
        return card;
    }

    public int getRange() {
        return range;
    }

    public SpecialPowerActivation getSpecialPowerActivation() {
        return specialPowerActivation;
    }

    public Player getOwner() {
        return owner;
    }

}