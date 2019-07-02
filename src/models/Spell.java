package models;

import models.Enums.SpellTarget;

import java.util.ArrayList;

public class Spell extends Card {
    private ArrayList<Buffs> buffs = new ArrayList<>();
    private SpellTarget target;
    private int numberOfTargetRows;
    private int numberOfTargetColumns;

    void effect(Cell... cells) {
    }

    void effect(Card... myCards) {
    }

    void effect(int... opponentCardsCoordinates) {
    }

    public boolean canCastSpell(int x, int y, Map map, Player player) {
        Cell cell = map.getCell(x, y);
        switch (this.target) {
            case MY_HERO:
                if (cell.getCard() instanceof Hero && cell.getCard().getOwner().equals(player)) {
                    return true;
                }
            case OPP_HERO:
                if (cell.getCard() instanceof Hero && !cell.getCard().getOwner().equals(player)) {
                    return true;
                }
            case OPP_MINION_ARROUND_MY_HERO:
                if (cell.getCard() instanceof Hero && cell.getCard().getOwner().equals(player)) {
                    return true;
                }
            case MY_MINIONS_ARROUND_OPP_HERO:
                if (cell.getCard() instanceof Hero && !cell.getCard().getOwner().equals(player)) {
                    return true;
                }
            default:
                return true;
        }
    }

    public ArrayList<Buffs> getBuffs() {
        return buffs;
    }

    public void setBuffs(ArrayList<Buffs> buffs) {
        this.buffs = buffs;
    }

    public SpellTarget getTarget() {
        return target;
    }

    public void setTarget(SpellTarget target) {
        this.target = target;
    }

    public int getNumberOfTargetRows() {
        return numberOfTargetRows;
    }

    public void setNumberOfTargetRows(int numberOfTargetRows) {
        this.numberOfTargetRows = numberOfTargetRows;
    }

    public int getNumberOfTargetColumns() {
        return numberOfTargetColumns;
    }

    public void setNumberOfTargetColumns(int numberOfTargetColumns) {
        this.numberOfTargetColumns = numberOfTargetColumns;
    }

}
