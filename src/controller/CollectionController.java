package controller;

import models.*;
import models.Enums.ErrorType;
import view.CollectionRequest;
import view.View;

class CollectionController {
    private static CollectionController collectionController = new CollectionController();
    private Collection collection;
    private View view = View.getInstance();

    private CollectionController() {
    }

    static CollectionController getInstance() {
        return collectionController;
    }

    void main(Collection collection) {
        CollectionRequest request;
        this.collection = collection;
        boolean isFinish = false;
        do {
            request = new CollectionRequest();
            request.getNewCommand();
            switch (request.getType()) {
                case EXIT:
                    isFinish = true;
                    break;
                case SHOW_COLLECTION_ITEMS_AND_CARDS:
                    showCollectionItemsAndCards();
                    break;
                case SEARCH_CARD_IN_COLLECTION:
                    search(request);
                    break;
                case SAVE:
                    save();
                    break;
                case CREATE_DECK:
                    createDeck(request);
                    break;
                case DELETE_DECK:
                    deleteDeck(request);
                    break;
                case ADD_CARD_TO_DECK:
                    addCardToDeck(request);
                    break;
                case REMOVE_CARD_FROM_DECK:
                    removeCardFromDeck(request);
                    break;
                case VALIDATE:
                    validateDeck(request);
                    break;
                case SELECT_DECK:
                    selectMainDeck(request);
                    break;
                case SHOW_ALL_DECKS:
                    showAllDecks();
                    break;
                case SHOW_DECK:
                    showDeck(request);
                    break;
                case HELP:
                    help();
                    break;
            }
        }
        while (!isFinish);
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
        if (!collection.isUsedDeckName(deckName)) {
            collection.createDeck(deckName);
        } else {
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

    public void help() {
        view.printCollectionMenuHelp(collection.toString());
    }

    private void save() {
    }

}