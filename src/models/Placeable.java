package models;

abstract class Placeable {
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
}
