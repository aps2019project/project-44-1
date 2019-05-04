package models;

public interface Goal {
    default boolean finishByDeath(Hero h1, Hero h2) {
        return h1.getHP() <= 0 || h2.getHP() <= 0;
    }

}
