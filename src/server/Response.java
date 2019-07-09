package server;

import javafx.scene.layout.AnchorPane;
import models.Account;
import models.Collection;
import models.Placeable;

import java.util.ArrayList;
import java.util.HashMap;

public class Response {
    private ResponseType responseType;
    private Environment environment;
    private String cardToSell;
    private Placeable cardToBuy;
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
    private int money;
    private double vValue;
    private ArrayList<Placeable> shopCards;
//    private AnchorPane paneToRemove;
    private Account account;

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

    public String getCardToSell() {
        return cardToSell;
    }

    public void setCardToSell(String cardToSell) {
        this.cardToSell = cardToSell;
    }

    public Placeable getCardToBuy() {
        return cardToBuy;
    }

    public void setCardToBuy(Placeable cardToBuy) {
        this.cardToBuy = cardToBuy;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public double getvValue() {
        return vValue;
    }

    public void setvValue(double vValue) {
        this.vValue = vValue;
    }

    public ArrayList<Placeable> getShopCards() {
        return shopCards;
    }

    public void setShopCards(ArrayList<Placeable> shopCards) {
        this.shopCards = shopCards;
    }

//    public AnchorPane getPaneToRemove() {
//        return paneToRemove;
//    }
//
//    public void setPaneToRemove(AnchorPane paneToRemove) {
//        this.paneToRemove = paneToRemove;
//    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
