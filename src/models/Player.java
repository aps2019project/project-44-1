package models;

import models.Enums.ErrorType;
import view.View;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Player implements Move {
    private Card[] hand = new Card[5];
    private int mana;
    private Deck deck;
    private int turnsFlagSaved;
    private int flagsCaptured;
    public static final int[] turnBeginMana = {2, 3, 4, 5, 6, 7, 8, 9};
    private String name;
    private Map myMap;
    private Card nextCardInHand;
    private Card selectedCard;

    Player(Deck deck, String name) {
        this.deck = deck.clone();
        this.name = name;
        for (Card card : this.deck.getCards()) {
            if (card instanceof Minion)
                card.setOwner(this);
        }
        this.deck.getHero().setOwner(this);
        Collections.shuffle(deck.getDeckCards());
        initializeHand();
    }

    void setMyMap(Map myMap) {
        this.myMap = myMap;
    }

    Map getMyMap() {
        return myMap;
    }

    public String getName() {
        return name;
    }

    public Card getNextCardInHand() {
        if (nextCardInHand == null) {
            return nextCardInHand;
        }
        return null;
    }

    public Card[] getHand() {
        return hand;
    }

    int getTurnsFlagSaved() {
        return turnsFlagSaved;
    }

    int getFlagsCaptured() {
        return flagsCaptured;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    Deck getDeck() {
        return deck;
    }

    private void initializeHand() {
        Iterator<Card> iterator = deck.getCards().iterator();
        int i = 0;
        try {
            while (i < 5) {
                hand[i] = iterator.next();
                i++;
                iterator.remove();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println(i);
        }
        nextCardInHand = deck.getCards().get(0);
    }

    String IDGenerator(String cardName) {
        return name + '_' + cardName + '_' + myMap.timesCardUsed(cardName, this);
    }

    public void insert(String cardName, int x, int y) {
        Card c = null;
        int i;
        for (i = 0; i < 5; i++) {
            if (hand[i] != null && hand[i].getName().equals(cardName)) {
                c = hand[i];
                break;
            }
        }
        if (c == null)
            View.getInstance().printError(ErrorType.INVALID_CARD_NAME);
        else if (c.getNeededMana() > mana)
            View.getInstance().printError(ErrorType.NO_ENOUGH_MANA);
        else if (invalidCoordination(x, y, 1, this))
            View.getInstance().printError(ErrorType.INVALID_TARGET);
        else if (c instanceof Spell && !((Spell) c).canCastSpell(x, y, myMap, this)) {
            View.getInstance().printError(ErrorType.INVALID_TARGET);
        } else {
            c.setInGameID(IDGenerator(cardName));
            Battle.relater(c, myMap.getCells()[x - 1][y - 1]);
            hand[i] = nextCardInHand;
            nextCardInHand = deck.getCards().get(0);
            View.getInstance().sout(cardName + " with " + c.getInGameID()
                    + " inserted to (" + x + "; " + y + ")");
        }
    }       // TODO: 5/7/2019 must handle for spells

    public boolean select(String inGameID) {        //returns true if selection was successful
        for (Card c : myMap.getPlayerCardsInMap(name)) {
            if (c.getInGameID().equals(inGameID)) {
                selectedCard = c;
                return true;
            }
        }
        return false;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    void increaseTurnsFlagSaved() {
        this.turnsFlagSaved++;
    }

    void increaseFlagsCaptured() {
        this.flagsCaptured++;
    }

    public String move(int x, int y) {
        if (getSelectedCard().isMovedThisTurn())
            return ErrorType.CARD_CANT_MOVE.getMessage();
        x--;
        y--;
        int distance = Map.getManhatanDistance(selectedCard.getMyCell(), myMap.getCells()[x][y]);
        if (!canMove(x, y, distance))
            return ErrorType.INVALID_TARGET.getMessage();
        else {
            return moveIT(x, y);
        }
    }

    private String moveIT(int x, int y) {
        selectedCard.getMyCell().setCard(null);
        Battle.relater(selectedCard, myMap.getCells()[x][y]);
        selectedCard.setMovedThisTurn(true);
        movingDirections(x, y, selectedCard.getMyCell().getX(), selectedCard.getMyCell().getY());
        return selectedCard.getInGameID() + " moved to " + (selectedCard.getMyCell().getX() + 1) + " "
                + (selectedCard.getMyCell().getY() + 1);
    }

    private boolean canMove(int x, int y, int distance) {
        boolean flag = true;
        switch (distance) {
            case 1:
                if (!myMap.getCell(x, y).isFree())
                    flag = false;
                break;
            case 2:
                if (!superInvalidCoordination(x, y, this))
                    flag = false;
                break;
            default:
                flag = false;
        }
        return flag;
    }

}