package controller.logicController;

import controller.fxmlControllers.ShopFxmlController;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.Enums.ErrorType;
import models.Shop;

import java.util.Timer;
import java.util.TimerTask;

import static models.Enums.ErrorType.NO_ERROR;

public class ShopController {
    private static ShopController shopController = new ShopController();
    private Shop shop = Shop.getInstance();
    private ShopFxmlController shopFxmlController;

    private ShopController() {
    }

    public static ShopController getInstance() {
        return shopController;
    }

    public void setShopFxmlController(ShopFxmlController shopFxmlController) {
        this.shopFxmlController = shopFxmlController;
    }

    public void sellCard(String cardName, AnchorPane pane) {
        cardName = cardName.split("\n")[0];
        boolean isDone = shop.sell(shop.getAccount().getCollection().getCardIDInCollection(cardName));
        if (isDone) {
            viewMessage("you sold\n" + cardName);
            shopFxmlController.collectionPane.getChildren().remove(pane);
        } else
            viewMessage("sell failed!!!");
        shopFxmlController.money.setText(String.valueOf(shop.getAccount().getMoney()));
    }

    public void buyCard(String cardName) {
        cardName = cardName.split("\n")[0];
        ErrorType error = shop.buy(cardName);
        if (error != NO_ERROR) {
            viewMessage(error.getMessage());
        } else {
            viewMessage("you bought \n" + cardName);
            shopFxmlController.fillPanes(shop.getCard(cardName), shopFxmlController.collectionPane,
                    false);
        }
        shopFxmlController.money.setText(String.valueOf(shop.getAccount().getMoney()));
    }

    private void viewMessage(String message) {
        Label message1 = shopFxmlController.message;
        message1.setText(message);
        message1.setVisible(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                message1.setVisible(false);
            }
        }, 1000);
    }

    public Shop getShop() {
        return shop;
    }

}
