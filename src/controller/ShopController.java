package controller;

import models.Shop;
import view.ShopRequest;
import view.View;

class ShopController {

    private Shop shop = new Shop();
    private View view = new View();

    void main() {
        boolean isFinish = false;
        do {
            ShopRequest request = new ShopRequest();
            request.getNewCommand();
            switch (request.getType()) {
                case EXIT:
                    isFinish = true;
                    break;
                case HELP:
                    view.printShopMenuHelp();
                    break;
                case SHOW_SHOP_ITEMS:
                    view.printShopItems(shop);
                    break;
                case SELL:
                    shop.sell(request.getID(1));
                    break;
                case BUY:
                    shop.buy(request.getName(1));
                    break;
                case SEARCH_COLLECTION:
                    shop.searchInCollection(request.getName(2));
                    break;
                case SEARCH_SHOP:
                    shop.searchInShop(request.getName(1));
                    break;
                case SHOW_COLLECTION_ITEMS_AND_CARDS:
                    view.printCollectionItems(null);
                    break;
            }

        }
        while (!isFinish);
    }

}
