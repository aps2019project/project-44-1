package models;

import models.Enums.BuffType;

public class Buffs {
    private String name;
    private BuffType buffType;
    private int power;
    private int duration;
    private boolean isCountiosly;
    //for power buff to know increase health or power
    private String target;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isCountiosly() {
        return isCountiosly;
    }

    public void setCountiosly(boolean contiously) {
        isCountiosly = contiously;
    }

    public void castBuff(Card card) {
        //holy buff should be handel in Card class when
        switch (buffType) {
            case POWER_BUFF:
                if (target.equals("HP")) {
                    Fight.increaseHP(power,card);
                }
                if (target.equals("AP")) {
                    Fight.increaseAP(power,card);
                }
                break;
            case POISION_BUFF:
                Fight.decreaseHP(1, false,card);
                break;
            case WEAKNESS_BUFF:
                if (target.equals("HP")) {
                    Fight.decreaseHP(power, false,card);
                }
                if (target.equals("AP")) {
                    Fight.decreaseAP(power,card);
                }
                break;
            case STUN_BUFF:
                card.setStuned(true);
                break;
            case DISARM_BUFF:
                card.setDisarmed(true);
        }
    }

}
