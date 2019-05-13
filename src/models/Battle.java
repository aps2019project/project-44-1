package models;

import models.Enums.BattleKind;
import models.Enums.BattleMode;
import models.Enums.ErrorType;
import view.View;

import java.util.ArrayList;

public class Battle implements Goal, Fight {
    private BattleKind battleKind;
    private Account first;
    private Account second;
    private Player firstPlayer;
    private Player secondPlayer;
    private Map map = new Map();
    private BattleMode battleMode;
    private int turn = 1;
    private int flagNumber;
    private boolean firstPlayerWon;

    public Battle(BattleKind battleKind, BattleMode battleMode, Account firstPlayer, Account secondPlayer, int flagNumber) {
        this.battleKind = battleKind;
        this.battleMode = battleMode;
        this.first = firstPlayer;
        this.second = secondPlayer;
        this.flagNumber = flagNumber;
        try {
            this.firstPlayer = new Player(first.getCollection().getMainDeck(), first.getUsername());
            this.firstPlayer.setMyMap(map);
            this.secondPlayer = new Player(second.getCollection().getMainDeck(), second.getUsername());
            this.secondPlayer.setMyMap(map);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        relater(getFirstPlayer().getDeck().getHero(), getMap().getCells()[2][0]);
        relater(getSecondPlayer().getDeck().getHero(), getMap().getCells()[2][8]);
        this.firstPlayer.getDeck().getHero().setInGameID(this.firstPlayer.IDGenerator(this.firstPlayer.getDeck().getHero().getName()));
        this.secondPlayer.getDeck().getHero().setInGameID(this.secondPlayer.IDGenerator(this.secondPlayer.getDeck().getHero().getName()));
        this.firstPlayer.getDeck().removeFromDeck(this.firstPlayer.getDeck().getHero());
        this.secondPlayer.getDeck().removeFromDeck(this.secondPlayer.getDeck().getHero());
    }

    BattleMode getBattleMode() {
        return battleMode;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public BattleKind getBattleKind() {
        return battleKind;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Map getMap() {
        return this.map;
    }

    static void relater(Placeable card, Cell cell) {
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
        getMap().getFlags().forEach(f -> cells.add(f.getMyCell()));
        if (cells.size() == 1 && getBattleMode() == BattleMode.CAPTURE_FLAG_1) {
            card = getMap().getFlags().get(0).getCarrier();       //type2
        }
        if (cells.size() > 1 && getBattleMode() == BattleMode.CAPTURE_FLAG_2)
            getMap().getFlags().forEach(Item::getCarrier);       //type3
        return "";
    }       //#TODO

    public void turnHandler() {       //method to handle all actions must occur at end of turn
        manaHandler();
        for (Card c : map.getAllCardsInMap()) {
            c.setMovedThisTurn(false);
            c.setAttackAvailable(true);
        }
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

    public String getPlayerName(int turn) {
        if (turn % 2 == 1) {
            return firstPlayer.getName();
        } else {
            return secondPlayer.getName();
        }
    }

    public Player getCurrentPlayer() {
        if (turn % 2 == 1) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }

    public ArrayList<Card> getMyCardsInMap() {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card : map.getAllCardsInMap()) {
            if (card.getOwner().getName().equals(getCurrentPlayer().getName()))
                cards.add(card);
        }
        return cards;
    }

    public ArrayList<Card> getOpponentCardsInMap() {
        ArrayList<Card> cards = new ArrayList<>();
        for (Card card : map.getAllCardsInMap()) {
            if (!card.getOwner().equals(getCurrentPlayer()))
                cards.add(card);
        }
        return cards;
    }

    public Placeable getCard(String cardID) {
        for (Card card : map.getAllCardsInMap()) {
            if (card != null && card.getInGameID().equals(cardID))
                return card;
        }
        return null;
    }

    public String getCardInfo(String cardID) {
        Placeable card = getCard(cardID);
        if (card instanceof Hero)
            return ((Hero) card).getHeroInfoInBattle();
        else if (card instanceof Minion)
            return ((Minion) card).getMinionInfoInBattle();
        else if (card instanceof Spell)
            return ((Spell) card).getSpellInfoInBattle();
        return null;
    }

    public Placeable selectCard(String cardID) {
        return map.getCard(cardID);
    }

    public int getTurn() {
        return turn;
    }

    public Account getFirst() {
        return first;
    }

    public Account getSecond() {
        return second;
    }

    public void castSpell(int x, int y, Spell spell) {
        ArrayList<Card> effectedCards = map.getEffectedCards(x, y, spell);
    }

    public void setSecond(Account second) {
        this.second = second;
    }

    private void manaHandler() {
        if (turn > 14) {
            getCurrentPlayer().setMana(9);
            return;
        }
        switch (turn % 2) {
            case 0:
                getCurrentPlayer().setMana(Player.turnBeginMana[turn / 2 - 1]);
                break;
            case 1:
                getCurrentPlayer().setMana(Player.turnBeginMana[(turn + 1) / 2 - 1]);
                // ArrayIndexOutOfBoundsException for turn 1
        }
    }

    public Hero getFirstPlayerHero() {
        for (Card card : map.getPlayerCardsInMap(getFirstPlayer().getName())) {
            if (card instanceof Hero) {
                return (Hero) card;
            }
        }
        return null;
    }

    public Hero getSecondPlayerHero() {
        for (Card card : map.getPlayerCardsInMap(getSecondPlayer().getName())) {
            if (card instanceof Hero) {
                return (Hero) card;
            }
        }
        return null;
    }


}