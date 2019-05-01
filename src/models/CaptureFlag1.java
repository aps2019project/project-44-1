package models;

public class CaptureFlag1 extends Battle {
    public CaptureFlag1(BattleKind battleKind, Account player1, Account player2) {
        super(battleKind,BattleMode.CAPTURE_FLAG_1 ,player1, player2);
    }
}
