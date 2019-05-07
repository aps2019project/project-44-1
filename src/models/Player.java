package models;

import models.Enums.ErrorType;
import view.View;

import java.util.Collections;
import java.util.Iterator;

public class Player {
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

    public Player(Deck deck, String name) {
        this.deck = deck;
        this.name = name;
        for (Card card : deck.getCards()) {
            if (card instanceof Minion)
                card.setOwner(this);
        }
        deck.getHero().setOwner(this);
        Collections.shuffle(deck.getDeckCards());
        initializeHand();
    }

    void setMyMap(Map myMap) {
        this.myMap = myMap;
    }

    public Map getMyMap() {
        return myMap;
    }

    public String getName() {
        return name;
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
        while (i < 5) {
            hand[i] = iterator.next();
            i++;
            iterator.remove();
        }
        nextCardInHand = deck.getCards().get(0);
    }

    private String IDGenerator(String cardName) {
        return name + '_' + cardName + '_' +
                (myMap.timesCardUsed(cardName) + 1);
    }

    public void insert(String cardName, int x, int y) {
        Card c = null;
        int i;
        View view = new View();
        for (i = 0; i < 5; i++) {
            if (hand[i].getName().equals(cardName)) {
                c = hand[i];
                break;
            }
        }
        if (c == null)
            view.printError(ErrorType.INVALID_CARD_NAME);
        else if (c.getNeededMana() > mana)
            view.printError(ErrorType.NO_ENOUGH_MANA);
        else if (invalidCoordination(x, y, 1))
            view.printError(ErrorType.INVALID_TARGET);
        else {
            c.setInGameID(IDGenerator(cardName));
            Battle.relater(c, myMap.getCells()[x - 1][y - 1]);
            hand[i] = nextCardInHand;
            nextCardInHand = deck.getCards().get(0);
            view.sout(cardName + " with " + c.getInGameID() + " inserted to (" + x + "; " + y + ")");
        }
    }       // TODO: 5/7/2019 must handle for spells

    private boolean invalidCoordination(int x, int y, int distance) {
        for (Card c : myMap.getPlayerCardsInMap(this.name)) {
            if (Map.getManhatanDistance(c.getCell(), myMap.getCells()[x - 1][y - 1]) == distance
                    || myMap.getCells()[x - 1][y - 1].isFree())
                return false;
        }
        return true;
    }

    public boolean select(String inGameID) {
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

    public String move(int x, int y) {
        int distance = Map.getManhatanDistance(selectedCard.getCell(),
                myMap.getCells()[x - 1][y - 1]);
        boolean flag = true;
        switch (distance) {
            case 1:
                if (invalidCoordination(x, y, distance))
                    flag = false;
                break;
            case 2:
                if (superInvalidCoordination(x, y))
                    flag = false;
                break;
            default:
                flag = false;
        }
        if (!flag)
            return ErrorType.INVALID_TARGET.getMessage();
        else {
            Battle.relater(selectedCard, myMap.getCells()[x - 1][y - 1]);
            return selectedCard.getInGameID() + " moved to " + selectedCard.getCell().getX()
                    + " " + selectedCard.getCell().getY();
        }
    }

    //if it want to move and there is opponent cards in his way
    private boolean superInvalidCoordination(int x, int y) {
        int yy = selectedCard.getCell().getY();
        int xx = selectedCard.getCell().getX();
        if (x == xx) {
            switch (yy - y) {
                case 2:
                    return invalidCoordination(x, y + 1, 1);
                case -2:
                    return invalidCoordination(x, y - 1, 1);
            }
        } else if (y == yy) {
            switch (xx - x) {
                case 2:
                    return invalidCoordination(x + 1, y, 1);
                case -2:
                    return invalidCoordination(x - 1, y, 1);
            }
        } //else if () ;
        return false;
    }

}