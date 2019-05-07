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
    private View view = new View();

    private ShopController(){

    }

    public static ShopController getInstance() {
        return shopController;
    }

    void main(Account account) {
        shop.setAccount(account);/* may make exception*/
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
                case SEARCH_COLLECTION:
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

    public void showShopCards() {
        view.printShopCards(shop.getCards());
    }

    public void sellCard(ShopRequest request) {
        int cardID = request.getCardID();
        boolean isDone = shop.sell(cardID);
        if (isDone) {
            view.successfulSellMessage();
        } else {
            view.unSuccessfulSellMessage();
        }
    }

    public void buyCard(ShopRequest request) {
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

    public void searchInShop(ShopRequest request) {
        String cardName = request.getCardName();
        shop.searchInShop(cardName);

    }

    public void searchInCollection(ShopRequest request) {
        String cardName = request.getCardName();
        int state = shop.searchInCollection(cardName);
        if (state != -1) {
            view.printCardInCollection(cardName, state);
        } else {
            view.printNoCardWithThisName(cardName);
        }
    }

    public void showCollectionCards() {
        view.printCollectionItems(shop.getCards(),true);
    }


}
