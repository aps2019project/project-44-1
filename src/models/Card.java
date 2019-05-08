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
    private boolean isAttackAvailable =true;

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

    Player getOwner() {
        return owner;
    }

    void decreaseHealth(int num, boolean isAttack) {
        this.HP -= num;
        // holy buff will work here if isAttack == true
    }

    boolean isInAttackRange(Cell src, Cell dest) {
        switch (this.getAttackType()) {
            case MELEE:
                return isInMeleeRange(src, dest);
            case HYBRID:
                return isInHybridRange(src, dest);
            default:
                return isInRangedRange(src, dest);
        }
    }

    private boolean isInRangedRange(Cell src, Cell dest) {
        return !isInMeleeRange(src, dest);

    }

    private boolean isInHybridRange(Cell src, Cell dest) {
        return true;

    }

    private boolean isInMeleeRange(Cell src, Cell dest) {
        if (Map.getManhatanDistance(src, dest) == 1) {
            return true;
        } else return Map.getManhatanDistance(src, dest) == 2 && src.getX() ==
                dest.getX() && src.getY() != dest.getY();

    }

    public boolean isAttackAvailable() {
        return isAttackAvailable;
    }

    public void setAttackAvailable(boolean attackAvailable) {
        this.isAttackAvailable = attackAvailable;
    }
}