package server;

import models.Account;
import models.Collection;
import models.Enums.ErrorType;
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
    private Account account;
    private String paneToRemoveID;
    private ErrorType shopErrorType;
    private ArrayList<Account> accounts;
    private HashMap<String,Account> onlineAccounts;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPaneToRemoveID() {
        return paneToRemoveID;
    }

    public void setPaneToRemoveID(String paneToRemoveID) {
        this.paneToRemoveID = paneToRemoveID;
    }

    public ErrorType getShopErrorType() {
        return shopErrorType;
    }

    public void setShopErrorType(ErrorType shopErrorType) {
        this.shopErrorType = shopErrorType;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public HashMap<String, Account> getOnlineAccounts() {
        return onlineAccounts;
    }

    public void setOnlineAccounts(HashMap<String, Account> onlineAccounts) {
        this.onlineAccounts = onlineAccounts;
    }
}
