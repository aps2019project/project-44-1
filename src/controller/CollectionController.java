package controller;

import models.Account;
import models.Collection;
import view.CollectionRequest;
import view.View;

import java.util.Collections;

class CollectionController {

    private Collection collection = new Collection();
    private View view = new View();

    void main(Account account) {
        this.collection = account.getCollection();
        boolean isFinish = false;
        do {
            CollectionRequest request = new CollectionRequest();
            request.setNewCommand();
            switch (request.getType()) {
                case EXIT:
                    isFinish = true;
                    break;
                case SHOW_COLLECTION_ITEMS:
                    collection.showCollection();
                    break;
                case SEARCH_DECK:
                    searchCard();
                    // TODO: 11/04/2019
                    //  i don't know it's needed to have separate function for searching cards o items?
                    break;
                case SAVE:
                    save();
                    break;
                case CREATE_DECK:
                    createDeck();
                    break;
                case DELETE_DECK:
                    deleteDeck();
                    break;
                case ADD_CARD_TO_DECK:
                    addCardToDeck();
                    break;
                case REMOVE_CARD_FROM_DECK:
                    removeCardFromDeck();
                    break;
                case VALIDATE:
                    validateDeck();
                    break;
                case SELECT_DECK:
                    selectMainDeck();
                    break;
                case SHOW_ALL_DECKS:
                    showAllDecks();
                    break;
                case SHOW_DECK:
                    showDeck();
                    break;
                case HELP:
                    help();
                    break;
                case LOGOUT:
                    isFinish = true;
                    break;
            }

        }
        while (!isFinish);
    }

    private void showCollection() {

    }

    private void searchCard() {

    }

    private void save() {

    }

    private void createDeck() {

    }

    private void deleteDeck() {

    }

    private void addCardToDeck() {

    }

    private void removeCardFromDeck() {

    }

    private void validateDeck() {

    }

    private void selectMainDeck() {

    }

    private void showAllDecks() {

    }

    private void showDeck() {

    }

    private void help() {

    }
}
