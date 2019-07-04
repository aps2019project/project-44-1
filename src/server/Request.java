package server;

import java.util.ArrayList;

public class Request {
    private RequestType requestType;
    private Environment environment;
    private String outhToken;
    private String username;
    private String password;
    private ArrayList<String> cardsToSell;
    private ArrayList<String> cardsToBuy;
    private String mainDeck;
    private String deckToRemove;
    private String deckToRemoveCardFrom;
    private ArrayList<String> cardsToRemoveFromDeck;

    public Request(Environment environment) {
        this.environment = environment;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getOuthToken() {
        return outhToken;
    }

    public void setOuthToken(String outhToken) {
        this.outhToken = outhToken;
    }

    public ArrayList<String> getCardsToSell() {
        return cardsToSell;
    }

    public void setCardsToSell(ArrayList<String> cardsToSell) {
        this.cardsToSell = cardsToSell;
    }

    public ArrayList<String> getCardsToBuy() {
        return cardsToBuy;
    }

    public void setCardsToBuy(ArrayList<String> cardsToBuy) {
        this.cardsToBuy = cardsToBuy;
    }

    public String getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(String mainDeck) {
        this.mainDeck = mainDeck;
    }

    public String getDeckToRemove() {
        return deckToRemove;
    }

    public void setDeckToRemove(String deckToRemove) {
        this.deckToRemove = deckToRemove;
    }

    public String getDeckToRemoveCardFrom() {
        return deckToRemoveCardFrom;
    }

    public void setDeckToRemoveCardFrom(String deckToRemoveCardFrom) {
        this.deckToRemoveCardFrom = deckToRemoveCardFrom;
    }

    public ArrayList<String> getCardsToRemoveFromDeck() {
        return cardsToRemoveFromDeck;
    }

    public void setCardsToRemoveFromDeck(ArrayList<String> cardsToRemoveFromDeck) {
        this.cardsToRemoveFromDeck = cardsToRemoveFromDeck;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
