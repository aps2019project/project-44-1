package models;

public abstract class Placeable implements Comparable<Placeable> {
    private Cell cell;
    private int neededMana;
    private int id;
    String name;
    int cost;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getNeededMana() {
        return neededMana;
    }

    public void setNeededMana(int neededMana) {
        this.neededMana = neededMana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Placeable o) {
        if (o.getClass() == this.getClass()) {
            return 0;
        }
        if (this instanceof Hero && (o instanceof Item || o instanceof Minion || o instanceof Spell)) {
            return 1;
        } else if (this instanceof Item && (o instanceof Minion || o instanceof Spell)) {
            return 1;
        } else if (this instanceof Minion && o instanceof Spell) {
            return 1;
        } else {
            return -1;
        }
    }
}
