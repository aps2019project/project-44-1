package controller;

import models.*;
import view.CollectionRequest;
import view.View;

class CollectionController {

    private Collection collection;
    private View view = new View();

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
                case SEARCH_DECK:
                    search(request);
                    break;
                case SAVE:
                    // TODO: 28/04/2019 save
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
                    collection.showAllDecks();
                    break;
                case SHOW_DECK:
                    collection.showDeck(request.getName(2));
                    break;
                case HELP:
                    view.printCollectionMenuHelp();
                    break;
            }

        }
        while (!isFinish);
    }

    public void showCollectionItemsAndCards() {
        view.printCollectionItems(collection.getCollectionCards());
    }

    public void search(CollectionRequest request) {
        String cardName = request.getName(1);
        if (collection.isInCollection(cardName)) {
            view.sout(collection.getCollectionID(cardName));
        } else {
            view.printError(ErrorType.CARD_NOT_FOUND_IN_COLLECTION);
        }
    }

    public void createDeck(CollectionRequest request) {
        String deckName = request.getName(2);
        if (!collection.isUsedDeckName(deckName)) {
            collection.createDeck(deckName);
        } else {
            view.printError(ErrorType.DUPLICATE_DECK);
        }
    }

    public void deleteDeck(CollectionRequest request) {
        String deckName = request.getName(2);
        if (collection.isUsedDeckName(deckName)) {
            collection.deleteDeck(deckName);
        } else {
            view.printError(ErrorType.DECK_NOT_FOUND);
        }
    }

    public void addCardToDeck(CollectionRequest request) {
        int cardID = request.getID(1);
        String deckName = request.getName(4);
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

    public void removeCardFromDeck(CollectionRequest request) {
        int cardID = request.getID(1);
        String deckName = request.getName(4);
        if (checkDeck(deckName)) return;
        if (!collection.getDeck(deckName).contains(cardID)) {
            view.printError(ErrorType.CARD_NOT_FOUND_IN_DECK);
            return;
        }
        collection.removeFromDeck(cardID, deckName);
    }

    public void validateDeck(CollectionRequest request) {
        String deckName = request.getName(2);
        if (checkDeck(deckName)) return;
        if (collection.validateDeck(deckName)) {
            view.printValidatedMessage();
        } else {
            view.printNotValidate();
        }
    }


    public void selectMainDeck(CollectionRequest request) {
        String deckName = request.getName(2);
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
}