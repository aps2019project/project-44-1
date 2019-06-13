package models;

public abstract class Placeable implements Comparable<Placeable> {
    private Cell cell;
    private int neededMana;
    private int ID;
    private String name;
    private int cost;

    void setCost(int cost) {
        this.cost = cost;
    }

    public Cell getMyCell() {
        return cell;
    }

    void setCell(Cell cell) {
        this.cell = cell;
    }

    int getNeededMana() {
        return neededMana;
    }

    void setNeededMana(int neededMana) {
        this.neededMana = neededMana;
    }

    int getID() {
        return ID;
    }

    void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int compareTo(Placeable o) {
        if (o.getClass() == this.getClass()) {
            return 0;
        }
        if (this instanceof Hero && (o instanceof Item || o instanceof Minion ||
                o instanceof Spell)) {
            return 1;
        } else if (this instanceof Item && (o instanceof Minion || o instanceof Spell)) {
            return 1;
        } else if (this instanceof Minion && o instanceof Spell) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
