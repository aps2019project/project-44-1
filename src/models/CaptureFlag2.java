package models;

public class CaptureFlag2 extends Battle {
    private int flagNumber;

    public CaptureFlag2(BattleKind battleKind, Account player1, Account player2, int flagNumber) {
        super(battleKind, BattleMode.CAPTURE_FLAG_2, player1, player2);
        this.flagNumber = flagNumber;
    }
}
