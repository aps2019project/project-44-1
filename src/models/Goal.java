package models;

import models.Enums.BattleMode;

public interface Goal {

    default boolean finishByDeath(Hero h1, Hero h2) {
        return h1.getHP() <= 0 || h2.getHP() <= 0;
    }

    default boolean finishBySaveFlag() {
        return false;
    }

    default boolean finishByCaptureFlag() {
        return false;
    }

    default boolean main(BattleMode battleMode, Hero h1, Hero h2) {
        if (finishByDeath(h1, h2))
            return true;
        switch (battleMode) {
            case CAPTURE_FLAG_1:
                if (finishBySaveFlag())
                    return true;
                break;
            case CAPTURE_FLAG_2:
                if (finishByCaptureFlag())
                    return true;
        }
        return false;
    }
}
