package controller;

import models.Account;
import models.Collection;
import models.Shop;
import view.ShopRequest;
import view.View;

class ShopController {

    private Shop shop = Shop.getInstance();
    private View view = new View();

    void main(Account account) {
        shop.setAccount(account);/** may make execption*/
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
            view.successfullSellMessage();
        } else {
            view.unSuccessfullSellMessage();
        }
    }

    public void buyCard(ShopRequest request) {

    }

    public void searchInShop(ShopRequest request) {

    }

    public void searchInCollection(ShopRequest request) {

    }

    public void showCollectionCards() {

    }


}
