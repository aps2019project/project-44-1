package models;

import models.Enums.BattleKind;
import models.Enums.BattleMode;

public class CaptureFlag1 extends Battle implements Goal {
    public CaptureFlag1(BattleKind battleKind, BattleMode battleMode, Account player1, Account player2) {
        super(battleKind, battleMode, player1, player2);
    }

    @Override
    public boolean finishByFlag() {
        return false;
    }
}