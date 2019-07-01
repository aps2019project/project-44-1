package controller.logicController;

import com.google.gson.Gson;
import models.*;
import models.Enums.ErrorType;
import view.request.CollectionRequest;
import view.View;

import java.io.FileWriter;
import java.io.IOException;

public class CollectionController {
    private static CollectionController collectionController = new CollectionController();
    private Collection collection;
    private View view = View.getInstance();
    private boolean forwardedFromShop;

    private CollectionController() {
    }

    public static CollectionController getInstance() {
        return collectionController;
    }

    private void showCollectionItemsAndCards() {
        view.printCollectionItems(collection.getCollectionCards(), false);
    }

    private void search(CollectionRequest request) {
        String cardName = request.getCardName();
        if (collection.isInCollection(cardName)) {
            view.sout(collection.getCollectionID(cardName));
        } else {
            view.printError(ErrorType.CARD_NOT_FOUND_IN_COLLECTION);
        }
    }

    private void createDeck(CollectionRequest request) {
        String deckName = request.getDeckName();
        try {
            collection.createDeck(deckName);
        } catch (Exceptions.DuplicateNameForDeck duplicateNameForDeck) {
            view.printError(ErrorType.DUPLICATE_DECK);
        }
    }

    private void deleteDeck(CollectionRequest request) {
        String deckName = request.getDeckName();
        if (collection.isUsedDeckName(deckName)) {
            collection.deleteDeck(deckName);
        } else {
            view.printError(ErrorType.DECK_NOT_FOUND);
        }
    }

    private void addCardToDeck(CollectionRequest request) {
        int cardID = request.getCardID();
        String deckName = request.getDeckName();
        Deck deck = collection.getDeck(deckName);
        if (collection.getCard(cardID) == null) {
            view.printError(ErrorType.CARD_NOT_FOUND_IN_COLLECTION);
            return;
        } else if (deck == null) {
            view.printError(ErrorType.DECK_NOT_FOUND);
            return;
        } else if (!collection.canAddCardToDeck(deck, cardID)) {
            view.printError(ErrorType.FULL_DECK);
            return;
        } else if (collection.getCard(cardID) instanceof Hero && !collection.canAddHero(deck)) {
            view.printError(ErrorType.HERO_SET_BEFORE);
            return;
        } else if (collection.getCard(cardID) instanceof Item && !collection.canAddItem(deck)) {
            view.printError(ErrorType.ITEM_SET_BEFORE);
            return;
        }
        collection.addToDeck(cardID, deckName);
    }

    private void removeCardFromDeck(CollectionRequest request) {
        int cardID = request.getCardID();
        String deckName = request.getDeckName();
        if (checkDeck(deckName)) return;
        if (!collection.getDeck(deckName).contains(cardID)) {
            view.printError(ErrorType.CARD_NOT_FOUND_IN_DECK);
            return;
        }
        collection.removeFromDeck(cardID, deckName);
    }

    private void validateDeck(CollectionRequest request) {
        String deckName = request.getDeckName();
        if (checkDeck(deckName)) return;
        if (collection.validateDeck(deckName)) {
            view.printValidatedMessage();
        } else {
            view.printNotValidate();
        }
    }

    private void selectMainDeck(CollectionRequest request) {
        String deckName = request.getDeckName();
        if (checkDeck(deckName)) return;
        collection.selectMainDeck(deckName);

    }

    private boolean checkDeck(String deckName) {
        Deck deck = collection.getDeck(deckName);
        if (deck == null) {
            view.printError(ErrorType.DECK_NOT_FOUND);
            return true;
        }
        return false;
    }

    private void showAllDecks() {
        view.printDecksInFormat(collection.getSortedDecks());
    }

    private void showDeck(CollectionRequest request) {
        String deckName = request.getDeckName();
        Deck deck = collection.getDeck(deckName);
        if (deck == null) {
            view.printError(ErrorType.DECK_NOT_FOUND);
            return;
        }
        view.printCardsInFormat(deck.getDeckCards(), false);

    }

    private void save() {
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void exportDeck(String deckName) {
        Deck deck = collection.getDeck(deckName);
        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter("deck.json");
            fileWriter.write(gson.toJson(deck));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isForwardedFromShop() {
        return forwardedFromShop;
    }

    public void setForwardedFromShop(boolean forwardedFromShop) {
        this.forwardedFromShop = forwardedFromShop;
    }

}
