package models;

import java.util.ArrayList;

public class Hero extends Card {
    private boolean isDisarmed;
    private boolean isStunned;
    private ArrayList<Buffs> buffs = new ArrayList<>();
    private int specialPowerCoolDown;

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

    @Override
    protected Hero clone() {
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

    public void setSpecialPowerCoolDown(int specialPowerCoolDown) {
        this.specialPowerCoolDown = specialPowerCoolDown;
    }

}
