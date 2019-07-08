package controller.logicController;

import com.google.gson.Gson;
import models.*;
import models.Enums.ErrorType;
import server.*;
import view.request.CollectionRequest;
import view.View;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CollectionController {
    private Collection collection;
    private View view = View.getInstance();
    private boolean forwardedFromShop;

    public CollectionController(Collection collection) {
        this.collection = collection;
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

    public void createDeck(String deckName, ResponseSender responseSender) {
        Response response = new Response(Environment.COLLECTION);
        response.setDeckToAdd(deckName);
        try {
            collection.createDeck(deckName);
            response.setResponseType(ResponseType.CREATE_DECK_SUCCESSFULLY);
        } catch (Exceptions.DuplicateNameForDeck duplicateNameForDeck) {
            response.setResponseType(ResponseType.DUPLICATE_DECK);
        }
        response.setCollection(collection);
        responseSender.sendResponse(response);
    }

    public void deleteDeck(Request request, ResponseSender responseSender) {
        collection.deleteDeck(request.getDeckToRemove());
        Response response = new Response(Environment.COLLECTION);
        response.setDeckToRemove(request.getDeckToRemove());
        response.setResponseType(ResponseType.SUCCESSFULLY_REMOVE_DECK);
        responseSender.sendResponse(response);
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

    public void selectMainDeck(Request request, ResponseSender responseSender) {
        Response response = new Response(Environment.COLLECTION);
        response.setResponseType(ResponseType.MAIN_DECK_SELECTED);
        collection.selectMainDeck(request.getMainDeck());
        responseSender.sendResponse(response);
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

    public void exportDeck(String deckName, ResponseSender responseSender) {
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

    public void addCardToDeck(Request request, ResponseSender responseSender) {
        Response response = new Response(Environment.COLLECTION);
        response.setDeckToAddCardTo(request.getDeckToAddCardTo());
        ArrayList<String> cards = request.getCardsToAddToDeck();
        Deck deck = collection.getDeck(request.getDeckToAddCardTo());
        int heroCounter = 0;
        int itemCounter = 0;
        int normalCardCounter = 0;
        for (String card : cards) {
            if (collection.getCard(card) instanceof Hero)
                heroCounter++;
            if (collection.getCard(card) instanceof Item)
                itemCounter++;
            if (collection.getCard(card) instanceof Minion || collection.getCard(card) instanceof Spell)
                normalCardCounter++;
        }
        if (heroCounter > 1 || (heroCounter == 1 && !collection.canAddHero(deck))) {
            response.setResponseType(ResponseType.MORE_THAN_ONE_HERO_ERROR);
        } else if (itemCounter > 1 || (itemCounter == 1 && !collection.canAddItem(deck))) {
            response.setResponseType(ResponseType.MORE_THAN_ONE_ITEM_ERROR);

        } else if (deck.getDeckCards().size() + normalCardCounter > 20) {
            response.setResponseType(ResponseType.MORE_THAN_20_NORMAL_CARD_ERROR);
        } else {
            response.setResponseType(ResponseType.SUCCESSFULLY_MOVE_CARD_TO_DECK);
            for (String card : cards) {
                deck.addToDeck(collection.getCard(card));
            }
        }
        //todo maybe need to add deck arraylist to the response
        response.setCollection(collection);
        responseSender.sendResponse(response);
    }

    public void removeCardFromDeck(Request request, ResponseSender responseSender) {
        Response response = new Response(Environment.COLLECTION);
        response.setResponseType(ResponseType.SUCCESSFULLY_REMOVE_CARD_FROM_DECK);
        ArrayList<String> cards = request.getCardsToRemoveFromDeck();
        Deck deck = collection.getDeck(request.getDeckToRemoveCardFrom());
        for (String card : cards) {
            deck.removeFromDeck(card);
        }
        response.setCollection(collection);
        responseSender.sendResponse(response);
    }

    public void importDeck(String importedDeck, ResponseSender responseSender) {

    }
}