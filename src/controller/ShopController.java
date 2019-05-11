package controller;

import models.Account;
import models.Enums.ErrorType;
import models.Shop;
import view.ShopRequest;
import view.View;

import static models.Enums.ErrorType.CARD_NOT_FOUND_IN_SHOP;
import static models.Enums.ErrorType.NO_ERROR;

class ShopController {
    private static ShopController shopController = new ShopController();
    private Shop shop = Shop.getInstance();
    private View view = View.getInstance();

    private ShopController() {
    }

    static ShopController getInstance() {
        return shopController;
    }

    void main(Account account) {
        shop.setAccount(account);
        boolean isFinish = false;
        do {
            ShopRequest request = new ShopRequest();
            request.getNewCommand();
            switch (request.getType()) {
                case EXIT:
                    isFinish = true;
                    break;
                case HELP:
                    help();
                    break;
                case SHOW_SHOP_CARDS:
                    showShopCards();
                    break;
                case SELL:
                    sellCard(request);
                    break;
                case BUY:
                    buyCard(request);
                    break;
                case SEARCH_CARD_IN_COLLECTION:
                    searchInCollection(request);
                    break;
                case SEARCH_SHOP:
                    searchInShop(request);
                    break;
                case SHOW_COLLECTION_ITEMS_AND_CARDS:
                    showCollectionCards();
                    break;
                case INVALID_COMMAND:
                    view.printError(ErrorType.INVALID_COMMAND);
            }
        }
        while (!isFinish);
    }

    public void help() {
        view.printShopMenuHelp(shop.toString());
    }

    private void showShopCards() {
        view.printShopCards(shop.getCards());
    }

    private void sellCard(ShopRequest request) {
        int cardID = request.getCardID();
        boolean isDone = shop.sell(cardID);
        if (isDone)
            view.successfulSellMessage();
        else
            view.unSuccessfulSellMessage();
    }

    private void buyCard(ShopRequest request) {
        ErrorType error;
        String cardName = request.getCardName();
        if (shop.getCard(cardName) != null) {
            error = shop.buy(cardName);
        } else {
            error = CARD_NOT_FOUND_IN_SHOP;
        }
        if (error != NO_ERROR) {
            view.printError(error);
        } else {
            view.successfulBuyMessage();
        }
    }

    private void searchInShop(ShopRequest request) {
        String cardName = request.getCardName();
        if (shop.searchInShop(cardName)) {
            view.printCardWasFound();
        } else {
            view.printError(CARD_NOT_FOUND_IN_SHOP);
        }
    }

    private void searchInCollection(ShopRequest request) {
        String cardName = request.getCardName();
        int state = shop.searchInCollection(cardName);
        if (state != -1) {
            view.printCardInCollection(cardName, state);
        } else {
            view.printNoCardWithThisName(cardName);
        }
    }

    private void showCollectionCards() {
        view.printCollectionItems(shop.getCards(), true);
    }

}