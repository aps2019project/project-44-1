package models;

import models.Enums.BattleKind;
import models.Enums.BattleMode;

import java.util.ArrayList;

public class Battle implements Goal, Fight {
    private BattleKind battleKind;
    private Player firstPlayer;
    private Player secondPlayer;
    private Map map = new Map();
    private BattleMode battleMode;
    private int turn = 1;
    private int flagNumber;
    private boolean firstPlayerWon;

    public Battle(BattleKind battleKind, BattleMode battleMode, Player firstPlayer, Player secondPlayer, int flagNumber) {
        this.battleKind = battleKind;
        this.battleMode = battleMode;
        this.firstPlayer = firstPlayer;
        firstPlayer.setMyMap(map);
        this.secondPlayer = secondPlayer;
        secondPlayer.setMyMap(map);
        this.flagNumber = flagNumber;
        relater(getFirstPlayer().getDeck().getHero(), getMap().getCells()[2][0]);
        relater(getSecondPlayer().getDeck().getHero(), getMap().getCells()[2][8]);
        firstPlayer.getDeck().removeFromDeck(firstPlayer.getDeck().getHero());
        secondPlayer.getDeck().removeFromDeck(secondPlayer.getDeck().getHero());
    }

    BattleMode getBattleMode() {
        return battleMode;
    }

    Player getSecondPlayer() {
        return secondPlayer;
    }

    public BattleKind getBattleKind() {
        return battleKind;
    }

    Player getFirstPlayer() {
        return firstPlayer;
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
            int HP1 = getFirstPlayer().getDeck().getHero().getHP();     //type1
            int HP2 = getSecondPlayer().getDeck().getHero().getHP();
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

    public boolean isFirstPlayerWon() {
        return firstPlayerWon;
    }

    void setFirstPlayerWon(boolean firstPlayerWon) {
        this.firstPlayerWon = firstPlayerWon;
    }

    public Player getCurrentPlayer() {
        if (turn % 2 == 1) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }

//    public Player getCardOwner(Placeable placeable){
//         get card owner by using username of card
//    }
    public ArrayList<Card> getMyCardsInMap(Player player) {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card : map.getAllCardsInMap()) {
            if (card instanceof Card && card.getOwner().equals(getCurrentPlayer())){
                cards.add(card);

            }
        }
        return cards;
    }

}