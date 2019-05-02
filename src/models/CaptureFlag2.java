package models;

import models.Enums.BattleKind;
import models.Enums.BattleMode;

public class CaptureFlag2 extends Battle {
    private int flagNumber;

    public CaptureFlag2(BattleKind battleKind, BattleMode battleMode, Account player1, Account player2, int flagNumber) {
        super(battleKind, battleMode, player1, player2);
        this.flagNumber = flagNumber;
    }
}
