package models;

import models.Enums.AttackType;
import models.Enums.SpecialPowerActivation;
import models.Enums.State;

public class Card extends Placeable implements Fight, Cloneable {
    private int AP;
    private int HP;
    private String specialPower;
    private String inGameID;
    private AttackType attackType;
    private int range;
    private SpecialPowerActivation specialPowerActivation;
    private Player owner;
    private boolean[] state = {true, false, false, false};

    void setOwner(Player owner) {
        this.owner = owner;
    }

    void setSpecialPowerActivation(SpecialPowerActivation specialPowerActivation) {
        this.specialPowerActivation = specialPowerActivation;
    }

    AttackType getAttackType() {
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

    public void setAP(int AP) {
        this.AP = AP;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public String getInGameID() {
        return inGameID;
    }

    void setInGameID(String inGameID) {
        this.inGameID = inGameID;
    }

    @Override
    protected Card clone() throws CloneNotSupportedException {
        Card newCard = new Card();
        newCard.setName(this.getName());
        newCard.setID(this.getID());
        newCard.setNeededMana(this.getNeededMana());
        newCard.HP = this.HP;
        newCard.AP = this.AP;
        newCard.attackType = this.attackType;
        newCard.inGameID = this.inGameID;
        newCard.range = this.range;
        newCard.owner = this.owner;
        newCard.specialPower = this.specialPower;
        newCard.specialPowerActivation = this.specialPowerActivation;
        newCard.state = this.state;
        return newCard;
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

    public boolean isAttackAvailable() {
        return state[State.ATTACK_AVAILABE.getIndex()];
    }

    void setAttackAvailable(boolean attackAvailable) {
        this.state[State.ATTACK_AVAILABE.getIndex()] = attackAvailable;
    }

    public String getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(String specialPower) {
        this.specialPower = specialPower;
    }

    public boolean isStuned() {
        return state[State.IS_STUNNED.getIndex()];
    }

    void setStuned(boolean stuned) {
        state[State.IS_STUNNED.getIndex()] = stuned;
    }

    public boolean isDisarmed() {
        return state[State.IS_DISARMED.getIndex()];
    }

    public void setDisarmed(boolean disarmed) {
        state[State.IS_DISARMED.getIndex()] = disarmed;
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

    boolean isMovedThisTurn() {
        return state[State.MOVED_THIS_TURN.getIndex()];
    }

    void setMovedThisTurn(boolean b) {
        this.state[State.MOVED_THIS_TURN.getIndex()] = b;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}