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
                    card.increaseHP(power);
                }
                if (target.equals("AP")) {
                    card.increaseAP(power);
                }
                break;
            case POISION_BUFF:
                card.decreaseHP(1, false);
                break;
            case WEAKNESS_BUFF:
                if (target.equals("HP")) {
                    card.decreaseHP(power, false);
                }
                if (target.equals("AP")) {
                    card.decreaseAP(power);
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
