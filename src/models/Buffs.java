package models;

import models.Enums.BuffType;

public class Buffs {
    private String name;
    private BuffType buffType;
    private int power;
    private int duration;
    private boolean isCountiosly;

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
        switch (this.getName()) {

        }
    }
}
