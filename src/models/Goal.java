package models;

public interface Goal {

    private int finishByDeath(Hero h1, Hero h2) {
        if (h2.getHP() <= 0)
            return 2;
        else if (h1.getHP() <= 0)
            return 1;
        else return -1;
    }

    private int finishBySaveFlag(Player p1, Player p2) {
        if (p2.getTurnsFlagSaved() == 6)
            return 2;
        else if (p1.getTurnsFlagSaved() == 6)
            return 1;
        else return -1;
    }

    private int finishByCaptureFlag(Battle battle) {
        if (battle.getFirstPlayer().getFlagsCaptured() >= battle.getFlagNumber() / 2 + 1)
            return 1;
        else if (battle.getSecondPlayer().getFlagsCaptured() >= battle.getFlagNumber() / 2 + 1)
            return 2;
        else return -1;
    }

    default boolean finishChecker(Battle battle) {
        switch (finishByDeath(battle.getFirstPlayer().getDeck().getHero(),
                battle.getSecondPlayer().getDeck().getHero())) {
            case 1:
                battle.setFirstPlayerWon(false);
                return true;
            case 2:
                battle.setFirstPlayerWon(true);
                return true;
        }
        switch (battle.getBattleMode()) {
            case CAPTURE_FLAG_1:
                switch (finishBySaveFlag(battle.getFirstPlayer(), battle.getSecondPlayer())) {
                    case 1:
                        battle.setFirstPlayerWon(true);
                        return true;
                    case 2:
                        battle.setFirstPlayerWon(false);
                        return true;
                }
                break;
            case CAPTURE_FLAG_2:
                switch (finishByCaptureFlag(battle)) {
                    case 1:
                        battle.setFirstPlayerWon(true);
                        return true;
                    case 2:
                        battle.setFirstPlayerWon(false);
                        return true;
                }
        }
        return false;
    }

}
