package controller;

import models.Account;
import models.Collection;
import view.CollectionRequest;
import view.View;


class CollectionController {

    private Collection collection;
    private View view = new View();

    void main(Collection collection) {
        this.collection = collection;
        boolean isFinish = false;
        do {
            CollectionRequest request = new CollectionRequest();
            request.getNewCommand();
            switch (request.getType()) {
                case EXIT:
                    isFinish = true;
                    break;
                case SHOW_COLLECTION_ITEMS:
                    collection.showCollection();
                    break;
                case SEARCH_DECK:
                    collection.search(request.getName(1));
                    break;
                case SAVE:
                    // TODO: 28/04/2019 save
                    break;
                case CREATE_DECK:
                    collection.createDeck(request.getName(2));
                    break;
                case DELETE_DECK:
                    collection.deleteDeck(request.getName(2));
                    break;
                case ADD_CARD_TO_DECK:
                    collection.addToDeck(request.getID(1), request.getName(4));
                    break;
                case REMOVE_CARD_FROM_DECK:
                    collection.removeFromDeck(request.getID(1), request.getName(4));
                    break;
                case VALIDATE:
                    collection.validateDeck(request.getName(2));
                    break;
                case SELECT_DECK:
                    collection.selectMainDeck(request.getName(2));
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
}