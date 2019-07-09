package server;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Request {
    private RequestType requestType;
    private Environment environment;
    private String outhToken;
    private String username;
    private String password;
    private String cardToSell;
    private String cardToBuy;
    private String deckToAdd;
    private String mainDeck;
    private String deckToRemove;
    private String deckToRemoveCardFrom;
    private String deckToAddCardTo;
    private String deckToValidate;
    private String exportedDeck;
    private String importedDeck;
    private ArrayList<String> cardsToRemoveFromDeck;
    private ArrayList<String> cardsToAddToDeck;
    private String searchedString;
//    private AnchorPane paneToSell;

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

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getCardToSell() {
        return cardToSell;
    }

    public void setCardToSell(String cardToSell) {
        this.cardToSell = cardToSell;
    }

    public String getCardToBuy() {
        return cardToBuy;
    }

    public void setCardToBuy(String cardToBuy) {
        this.cardToBuy = cardToBuy;
    }

    public String getSearchedString() {
        return searchedString;
    }

    public void setSearchedString(String searchedString) {
        this.searchedString = searchedString;
    }

//    public AnchorPane getPaneToSell() {
//        return paneToSell;
//    }
//
//    public void setPaneToSell(AnchorPane paneToSell) {
//        this.paneToSell = paneToSell;
//    }

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

    public String getDeckToAdd() {
        return deckToAdd;
    }

    public void setDeckToAdd(String deckToAdd) {
        this.deckToAdd = deckToAdd;
    }

    public ArrayList<String> getCardsToAddToDeck() {
        return cardsToAddToDeck;
    }

    public void setCardsToAddToDeck(ArrayList<String> cardsToAddToDeck) {
        this.cardsToAddToDeck = cardsToAddToDeck;
    }

    public String getDeckToAddCardTo() {
        return deckToAddCardTo;
    }

    public void setDeckToAddCardTo(String deckToAddCardTo) {
        this.deckToAddCardTo = deckToAddCardTo;
    }

    public String getDeckToValidate() {
        return deckToValidate;
    }

    public void setDeckToValidate(String deckToValidate) {
        this.deckToValidate = deckToValidate;
    }

    public String getExportedDeck() {
        return exportedDeck;
    }

    public void setExportedDeck(String exportedDeck) {
        this.exportedDeck = exportedDeck;
    }

    public String getImportedDeck() {
        return importedDeck;
    }

    public void setImportedDeck(String importedDeck) {
        this.importedDeck = importedDeck;
    }
}
