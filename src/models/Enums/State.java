package models.Enums;

public enum State{
    ATTACK_AVAILABE(0),
    IS_STUNNED(1),
    IS_DISARMED(2),
    MOVED_THIS_TURN(3);

    private int index;

    State(int index){
        this.index = index;
    }

    public int getIndex(){
        return this.index;
    }

}
