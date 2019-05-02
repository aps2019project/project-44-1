package models;

import controller.AttackType;
import models.Enums.BattleKind;
import models.Enums.BattleMode;

import java.util.ArrayList;
import java.util.Random;

public class Battle {
    private BattleKind battleKind;
    private Account player1;
    private Account player2;
    private Map map = new Map();
    private static int flagNum;
    private BattleMode battleMode;

    public Battle(BattleKind battleKind, BattleMode battleMode, Account player1, Account player2) {
        this.battleKind = battleKind;
        this.battleMode = battleMode;
        this.player1 = player1;
        this.player2 = player2;
        relater(getPlayer1().getMainDeck().getHero(), getMap().getCells()[2][0]);
        relater(getPlayer2().getMainDeck().getHero(), getMap().getCells()[2][8]);
        player1.getMainDeck().removeFromDeck(player1.getMainDeck().getHero());
        player2.getMainDeck().removeFromDeck(player2.getMainDeck().getHero());
        player1.initializeHand();
        player2.initializeHand();
    }

    private BattleMode getBattleMode() {
        return battleMode;
    }

    public static int getFlagNum() {
        return flagNum;
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

    private static void relater(Placeable card, Cell cell) {
        card.setCell(cell);
        if (card instanceof Card)
            cell.setPlaceable((Card) card);
        else cell.setItem((Item) card);
    }

    void captureFlag1Handlere() {
        int turnCounter;
        boolean flagCaptured;
        Random random = new Random();
        relater(getMap().getFlags().get(0),
                getMap().getCells()[random.nextInt(5)][random.nextInt(9)]);

    }

    @Override
    public String toString() {
        int HP1 = getPlayer1().getMainDeck().getHero().getHP();     //type1
        int HP2 = getPlayer2().getMainDeck().getHero().getHP();

        ArrayList<Cell> cells = new ArrayList<>();      //^_^
        Card card;
        getMap().getFlags().forEach(f -> cells.add(f.getCell()));
        if (cells.size() == 1 && getBattleMode() == BattleMode.CAPTURE_FLAG_1) {
            card = getMap().getFlags().get(0).getCarrier();       //type2
        }
        if (cells.size() > 1 && getBattleMode() == BattleMode.CAPTURE_FLAG_2)
            getMap().getFlags().forEach(f -> f.getCarrier());       //type3
        return "";
    }       //#TODO

}
