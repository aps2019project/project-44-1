package controller;

import models.Shop;
import view.ShopRequest;
import view.ShopView;

public class ShopController {

    private Shop shop = new Shop();
    private ShopView view = new ShopView();


    public void main() {
        boolean isFinish = false;
        do {
            ShopRequest request = new ShopRequest();
            request.setNewCommand();
            if (request.getType().equals("exit")) {
                isFinish = true;
            }
            if (request.getType().equals("help")) {
                help(view);
            }
            if (request.getType().equals("show shop")) {
                showShopCards(view);
            }
            if (request.getType().equals("sell card")) {
                sell(view, request);
            }
            if (request.getType().equals("buy card")) {
                buy(view, request);
            }
            if (request.getType().equals("search for a card in collection")) {
                searchInCollection(view, request);
            }
            if (request.getType().equals("search for a card in shop")) {
                searchInShop(view, request);
            }
            if (request.getType().equals("show collection")) {
                showCollection(view);
            }

        }
        while (!isFinish);
    }

    private void help(ShopView view) {

    }

    private void showShopCards(ShopView view) {

    }

    private void sell(ShopView view, ShopRequest request) {

    }

    private void buy(ShopView view, ShopRequest request) {

    }

    private void searchInCollection(ShopView view, ShopRequest request) {
        // not sure about this
    }

    private void searchInShop(ShopView view, ShopRequest request) {

    }

    private void showCollection(ShopView view) {
        // not sure about this
    }
}
