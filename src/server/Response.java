package server;

import models.Collection;

import java.util.ArrayList;
import java.util.HashMap;

public class Response {
    private ResponseType responseType;
    private Environment environment;
    private ArrayList<String> cardsToSell;
    private ArrayList<String> cardsToBuy;
    private String mainDeck;
    private String deckToRemove;
    private String deckToRemoveCardFrom;
    private String deckToAdd;
    private String deckToAddCardTo;
    private ArrayList<String> cardsToAddToDeck;
    private ArrayList<String> cardsToRemoveFromDeck;
    private String authToken;
    private HashMap<String, Integer> remainingCardsInShop;
    private Collection collection;

    public Response(Environment environment) {
        this.environment = environment;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
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

    public String getDeckToAdd() {
        return deckToAdd;
    }

    public void setDeckToAdd(String deckToAdd) {
        this.deckToAdd = deckToAdd;
    }

    public String getDeckToAddCardTo() {
        return deckToAddCardTo;
    }

    public void setDeckToAddCardTo(String deckToAddCardTo) {
        this.deckToAddCardTo = deckToAddCardTo;
    }

    public ArrayList<String> getCardsToAddToDeck() {
        return cardsToAddToDeck;
    }

    public void setCardsToAddToDeck(ArrayList<String> cardsToAddToDeck) {
        this.cardsToAddToDeck = cardsToAddToDeck;
    }

    public HashMap<String, Integer> getRemainingCardsInShop() {
        return remainingCardsInShop;
    }

    public void setRemainingCardsInShop(HashMap<String, Integer> remainingCardsInShop) {
        this.remainingCardsInShop = remainingCardsInShop;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

}
