package models;

import models.Enums.BattleKind;
import models.Enums.BattleMode;

import java.util.ArrayList;

public class Battle implements Goal, Fight {
    private BattleKind battleKind;
    private Player player1;
    private Player player2;
    private Map map = new Map();
    private BattleMode battleMode;
    private int turn = 1;
    private int flagNumber;
    private boolean player1Won;

    public Battle(BattleKind battleKind, BattleMode battleMode, Player player1, Player player2, int flagNumber) {
        this.battleKind = battleKind;
        this.battleMode = battleMode;
        this.player1 = player1;
        player1.setMyMap(map);
        this.player2 = player2;
        player2.setMyMap(map);
        this.flagNumber = flagNumber;
        relater(getPlayer1().getDeck().getHero(), getMap().getCells()[2][0]);
        relater(getPlayer2().getDeck().getHero(), getMap().getCells()[2][8]);
        player1.getDeck().removeFromDeck(player1.getDeck().getHero());
        player2.getDeck().removeFromDeck(player2.getDeck().getHero());
    }

    BattleMode getBattleMode() {
        return battleMode;
    }

    Player getPlayer2() {
        return player2;
    }

    public BattleKind getBattleKind() {
        return battleKind;
    }

    Player getPlayer1() {
        return player1;
    }

    private Map getMap() {
        return map;
    }

    private static void relater(Placeable card, Cell cell) {
        card.setCell(cell);
        if (card instanceof Card)
            cell.setPlaceable((Card) card);
        else cell.setItem((Item) card);
    }

    @Override
    public String toString() {
        if (this.getBattleMode() == BattleMode.DEATH_MATCH) {
            int HP1 = getPlayer1().getDeck().getHero().getHP();     //type1
            int HP2 = getPlayer2().getDeck().getHero().getHP();
            return "HP of first player Hero is " + HP1 + "\n" +
                    "HP of second player Hero is " + HP2;
        } else if (this.battleMode == BattleMode.CAPTURE_FLAG_1) {

        }

        ArrayList<Cell> cells = new ArrayList<>();      //^_^
        Card card;
        getMap().getFlags().forEach(f -> cells.add(f.getCell()));
        if (cells.size() == 1 && getBattleMode() == BattleMode.CAPTURE_FLAG_1) {
            card = getMap().getFlags().get(0).getCarrier();       //type2
        }
        if (cells.size() > 1 && getBattleMode() == BattleMode.CAPTURE_FLAG_2)
            getMap().getFlags().forEach(Item::getCarrier);       //type3
        return "";
    }       //#TODO

    public boolean whosTurn() {
        return turn % 2 == 1;
    }

    public void turnHandler() {       //method to handle all actions must occur at end of turn
        turn++;
    }

    int getFlagNumber() {
        return flagNumber;
    }

    public boolean isPlayer1Won() {
        return player1Won;
    }

    void setPlayer1Won(boolean player1Won) {
        this.player1Won = player1Won;
    }

}