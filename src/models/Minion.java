package models;

public class Minion extends Card {
    private String specialPowerActivation;

    @Override
    protected Minion clone() {
        Minion minion = new Minion();
        minion.setOwner(this.getOwner());
        minion.setAttackType(this.getAttackType());
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

    public void setSpecialPowerActivation(String specialPowerActivation) {
        this.specialPowerActivation = specialPowerActivation;
    }

}
