package controller;

import models.Collection;
import view.CollectionRequest;
import view.CollectionView;


public class CollectionController {

    private Collection collection = new Collection();
    private CollectionView view = new CollectionView();

    public void main(Collection collection) {
        this.collection = collection;
        boolean isFinish = false;
        do {
            CollectionRequest request = new CollectionRequest();
            request.getNewCommand();
            if (request.getType().equals("exit")) {
                isFinish = true;
            }
            if (request.getType().equals("show")) {
                showCollection(view);
            }
            if (request.getType().equals("search")) {
                searchCard(view, request);
                // TODO: 11/04/2019
                //  i don't know it's needed to have separate function for searching cards o items?
            }
            if (request.getType().equals("save")) {
                save(view);
            }
            if (request.getType().equals("create deck")) {
                createDeck(view, request);
            }
            if (request.getType().equals("delete deck")) {
                deleteDeck(view, request);
            }
            if (request.getType().equals("add card to deck")) {
                addCardToDeck(view, request);
            }
            if (request.getType().equals("remove card from deck")) {
                removeCardFromDeck(view, request);
            }
            if (request.getType().equals("validate deck")) {
                validateDeck(view, request);
            }
            if (request.getType().equals("select main deck")) {
                selectMainDeck(view, request);
            }
            if (request.getType().equals("show all decks")) {
                showAllDecks(view, request);
            }
            if (request.getType().equals("show deck by deck name")) {
                showDeck(view, request);
            }
            if (request.getType().equals("help")) {
                help(view);
            }

        }
        while (!isFinish);
    }

    public void showCollection(CollectionView view) {

    }

    public void searchCard(CollectionView view, CollectionRequest request) {

    }

    public void save(CollectionView view) {

    }

    public void createDeck(CollectionView view, CollectionRequest request) {

    }

    public void deleteDeck(CollectionView view, CollectionRequest request) {

    }

    public void addCardToDeck(CollectionView view, CollectionRequest request) {

    }

    public void removeCardFromDeck(CollectionView view, CollectionRequest request) {

    }

    public void validateDeck(CollectionView view, CollectionRequest request) {

    }

    public void selectMainDeck(CollectionView view, CollectionRequest request) {

    }

    public void showAllDecks(CollectionView view, CollectionRequest request) {

    }

    public void showDeck(CollectionView view, CollectionRequest request) {

    }

    public void help(CollectionView view) {

    }
}
