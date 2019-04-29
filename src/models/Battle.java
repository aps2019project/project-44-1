package models;

public class Battle {
    private BattleKind battleKind;
    private Account player1;
    private Account player2;
    private Map map = new Map();

    public Battle(BattleKind battleKind, Account player1, Account player2) {
        this.battleKind = battleKind;
        this.player1 = player1;
        this.player2 = player2;
        relater(getPlayer1().getMainDeck().getHero(),getMap().getCells()[2][0]);
        relater(getPlayer2().getMainDeck().getHero(),getMap().getCells()[2][8]);
    }

    private Account getPlayer2() {
        return player2;
    }

    public void setPlayer2(Account player2) {
        this.player2 = player2;
    }

    public BattleKind getBattleKind() {
        return battleKind;
    }

    public void setBattleKind(BattleKind battleKind) {
        this.battleKind = battleKind;
    }

    private Account getPlayer1() {
        return player1;
    }

    public void setPlayer1(Account player1) {
        this.player1 = player1;
    }

    private Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    private void relater(Card card, Cell cell) {
        card.setCell(cell);
        cell.setPlaceable(card);
    }
}
