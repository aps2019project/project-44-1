package controller.logicController;

import com.google.gson.Gson;
import models.*;
import server.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CollectionController {
    private Collection collection;

    public CollectionController(Collection collection) {
        this.collection = collection;
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

    public void selectMainDeck(Request request, ResponseSender responseSender) {
        Response response = new Response(Environment.COLLECTION);
        response.setResponseType(ResponseType.MAIN_DECK_SELECTED);
        collection.selectMainDeck(request.getMainDeck());
        responseSender.sendResponse(response);
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void exportDeck(String deckName, ResponseSender responseSender) {
        Deck deck = collection.getDeck(deckName);
        Gson gson = new Gson();
        File file = new File("deck.json");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(deck));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Response response = new Response(Environment.COLLECTION);
        ResponseType exportDeck = ResponseType.EXPORT_DECK;
        exportDeck.setMessage(file.getAbsolutePath());
        response.setResponseType(exportDeck);
        responseSender.sendResponse(response);
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

        } else if (deck.getCards().size() + normalCardCounter > 20) {
            response.setResponseType(ResponseType.MORE_THAN_20_NORMAL_CARD_ERROR);
        } else {
            response.setResponseType(ResponseType.SUCCESSFULLY_MOVE_CARD_TO_DECK);
            for (String card : cards) {
                deck.addToDeck(collection.getCard(card));
            }
        }
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