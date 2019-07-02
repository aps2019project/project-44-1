package models;

import models.Enums.BattleKind;
import models.Enums.BattleMode;

import java.util.ArrayList;
import java.util.Random;

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
    private int prize = 1000;

    public Battle(BattleKind battleKind, BattleMode battleMode, Account firstPlayer,
                  Account secondPlayer, int flagNumber, int... prize) {
        this.battleKind = battleKind;
        this.battleMode = battleMode;
        this.first = firstPlayer;
        this.second = secondPlayer;
        this.flagNumber = flagNumber;
        if (prize.length == 1)
            this.prize = prize[0];
        this.firstPlayer = new Player(first.getCollection().getMainDeck(),
                first.getUsername());
        this.firstPlayer.setMyMap(map);
        this.secondPlayer = new Player(second.getCollection().getMainDeck(),
                second.getUsername());
        this.secondPlayer.setMyMap(map);
        if (!battleMode.equals(BattleMode.DEATH_MATCH))
            putFlags();
        putHeroes();
    }

    private void putHeroes() {
        relater(getFirstPlayer().getDeck().getHero(), getMap().getCells()[2][0]);
        relater(getSecondPlayer().getDeck().getHero(), getMap().getCells()[2][8]);
        this.firstPlayer.getDeck().getHero().setInGameID(this.firstPlayer.
                IDGenerator(this.firstPlayer.getDeck().getHero().getName()));
        this.secondPlayer.getDeck().getHero().setInGameID(this.secondPlayer.
                IDGenerator(this.secondPlayer.getDeck().getHero().getName()));
        this.firstPlayer.getDeck().removeFromDeck(this.firstPlayer.getDeck().getHero());
        this.secondPlayer.getDeck().removeFromDeck(this.secondPlayer.getDeck().getHero());
    }

    private void putFlags() {
        Random random = new Random();
        for (int i = 0; i < flagNumber; i++) {
            int x = random.nextInt(5);
            int y = random.nextInt(9);
            if (x == 0 || y == 0) {
                i--;
                continue;
            }
            Cell cell = map.getCell(x, y);
            if (cell.isFree() && cell.getItem() == null) {
                Item flag = new Item();
                flag.flagInitialize(i);
                relater(flag, cell);
                map.putFlagInMap(flag);
            } else
                i--;
        }
    }

    BattleMode getBattleMode() {
        return battleMode;
    }

    public int getPrize() {
        return prize;
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

    int getFlagNumber() {
        return flagNumber;
    }

    public boolean isFirstPlayerWon() {
        return firstPlayerWon;
    }

    void setFirstPlayerWon(boolean firstPlayerWon) {
        this.firstPlayerWon = firstPlayerWon;
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

    //-----------------------------------------getter & setter & constructing ^_^

    static void relater(Placeable card, Cell cell) {
        card.setCell(cell);
        if (card instanceof Card)
            cell.setPlaceable((Card) card);
        else cell.setItem((Item) card);
    }

    /**
     * method to handle all actions must occur at end of turn
     */
    public void turnHandler() {
        manaHandler();
        for (Card c : map.getAllCardsInMap()) {
            c.setMovedThisTurn(false);
            c.setAttackAvailable(true);
        }
        turn++;
        increaseTurnsFlagSaved();
    }

    private void increaseTurnsFlagSaved() {
        if (battleMode.equals(BattleMode.CAPTURE_FLAG_1)) {
            for (Item f : map.getFlags()) {
                Player player = f.getCarrier().getOwner();
                if (player != null) {
                    if (!player.equals(firstPlayer) && !player.equals(secondPlayer))
                        throw new NullPointerException();
                    player.increaseTurnsFlagSaved();
                }
            }
        }
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

    public void castSpell(int x, int y, Spell spell) {
        ArrayList<Card> effectedCards = map.getEffectedCards(x, y, spell);
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

    Hero getFirstPlayerHero() {
        for (Card card : map.getPlayerCardsInMap(getFirstPlayer().getName())) {
            if (card instanceof Hero) {
                return (Hero) card;
            }
        }
        return null;
    }

    Hero getSecondPlayerHero() {
        for (Card card : map.getPlayerCardsInMap(getSecondPlayer().getName())) {
            if (card instanceof Hero) {
                return (Hero) card;
            }
        }
        return null;
    }

}