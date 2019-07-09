package client;

import Main.Main;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonStreamParser;
import controller.fxmlControllers.*;
import controller.logicController.AccountController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import models.Game;
import javafx.scene.control.Label;
import models.*;
import server.Response;
import server.ResponseType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import static models.Enums.ErrorType.NO_ERROR;
import static server.RequestType.*;
import static server.ResponseType.*;
import static server.ResponseType.ACCOUNT_MONEY;
import static server.ResponseType.ENTER_COLLECTION;
import static server.ResponseType.GET_SHOP_CARDS;
import static server.ResponseType.SEARCH_IN_SHOP;


public class ResponseHandler extends Thread {
    private static ResponseHandler RESPONSE_HANDLER = new ResponseHandler();
    private JsonStreamParser jsonStreamParser;
    private Gson gson = new Gson();
    private Response response;
    private CollectionFxmlController collectionController;
    private ShopFxmlController shopFxmlController;
    private MainMenuController mainMenuController;

    public static ResponseHandler getInstance() {
        return RESPONSE_HANDLER;
    }

    public void setJsonStreamParser(InputStream inputStream) {
        jsonStreamParser = new JsonStreamParser(new BufferedReader(new InputStreamReader(inputStream)));
    }

    private ResponseHandler() {
    }

    @Override
    public void run() {
        try {
            while (jsonStreamParser.hasNext()) {
                this.response = gson.fromJson(jsonStreamParser.next(), Response.class);
                new Thread(this::handleResponse).start();
            }
        } catch (JsonIOException ignored) {

        }
    }

    private void handleResponse() {
        switch (response.getEnvironment()) {
            case LOGIN_PAGE:
                handleLoginPageResponse();
                break;
            case COLLECTION:
                handleCollectionResponse();
                break;
            case BATTLE:
                handleBattleResponse();
                break;
            case SHOP:
                handleShopResponse();
                break;
            case LEADER_BOARD:
                handleLeaderboardResponse();
                break;
            case MAIN_MENU:
                handleMainMenu();
        }
    }

    private void handleMainMenu() {
        switch (response.getResponseType()){
            case SHOW_MATCH_HISTORY:
                MatchHistoriesController.setAccount(response.getAccount());
                showMatchHistory();
                break;
        }

    }

    private void showMatchHistory() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/matchHistories.fxml"));
                try {
                    Main.getStage().getScene().setRoot(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void handleLoginPageResponse() {
        if (response.getResponseType().equals(SUCCESSFUL_SIGN_IN)) {
            loadMainMenu();
            Main.setToken(response.getAuthToken());
        } else
            Platform.runLater(() -> Main.getLoginPageController().appearLabel(response.getResponseType().getMessage()));
    }

    private void loadMainMenu() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/mainMenu.fxml"));
                mainMenuController = loader.getController();
                try {
                    Main.getStage().getScene().setRoot(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void handleCollectionResponse() {
        if (response.getCollection() != null)
            CollectionFxmlController.setCollection(response.getCollection());
        if (CREATE_DECK_SUCCESSFULLY.equals(response.getResponseType())) {
            Platform.runLater(() -> collectionController.decks.getItems().add(response.getDeckToAdd()));
        } else if (SUCCESSFULLY_REMOVE_DECK.equals(response.getResponseType())) {
            removeDeck(response.getDeckToRemove());
        } else if (DUPLICATE_DECK.equals(response.getResponseType())) {
            Platform.runLater(() -> collectionController.makeAlert("Error while making deck", "This name was used before!", Alert.AlertType.ERROR));
        } else if (MORE_THAN_ONE_HERO_ERROR.equals(response.getResponseType()) || MORE_THAN_20_NORMAL_CARD_ERROR.equals(response.getResponseType()) || MORE_THAN_ONE_ITEM_ERROR.equals(response.getResponseType())) {
            Platform.runLater(() -> collectionController.makeAlert("Error while adding cards to deck", ((ResponseType) response.getResponseType()).getMessage(), Alert.AlertType.ERROR));
        } else if (SUCCESSFULLY_MOVE_CARD_TO_DECK.equals(response.getResponseType()) || SUCCESSFULLY_REMOVE_CARD_FROM_DECK.equals(response.getResponseType())) {
            Platform.runLater(() -> collectionController.updateDeckCards());
        } else if (MAIN_DECK_SELECTED.equals(response.getResponseType())) {
            Platform.runLater(() -> collectionController.makeAlert("new main deck selected", null, Alert.AlertType.INFORMATION));
        } else if (ENTER_COLLECTION.equals(response.getResponseType())) {
            loadCollection();
        }
    }

    private void loadCollection() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                CollectionFxmlController.setCollection(response.getCollection());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/collectionPage.fxml"));
                try {
                    Main.getStage().getScene().setRoot(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ResponseHandler.getInstance().setCollectionController(loader.getController());
            }
        });
    }


    private void removeDeck(String deckToRemove) {
        Platform.runLater(() -> {
            collectionController.decks.getItems().remove(deckToRemove);
            collectionController.deckCardsFlowPane.getChildren().clear();
            collectionController.decks.setValue(null);
        });

    }

    private void handleBattleResponse() {
        switch (response.getResponseType()) {
            case MAIN_DECK_IS_VALID:
                loadBattleMenu();
                AccountController.getInstance().setAccount(response.getAccount());
                break;
            case MAIN_DECK_IS_NOT_VALID:
                Platform.runLater(() -> mainMenuController.appearLabel(response.getResponseType().getMessage()));
                break;
        }
    }

    private void loadBattleMenu() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/battleMenu.fxml"));
                try {
                    Main.getStage().getScene().setRoot(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void handleShopResponse() {
        if (ACCOUNT_MONEY.equals(response.getResponseType())) {
            Platform.runLater(() -> shopFxmlController.money.setText(String.valueOf(response.getMoney())));
        } else if (SEARCH_IN_SHOP.equals(response.getResponseType())) {
            Platform.runLater(() -> shopFxmlController.shop.setVvalue(response.getvValue()));
        } else if (GET_SHOP_CARDS.equals(response.getResponseType())) {
            Platform.runLater(this::getShopCards);
        } else if (SUCCESSFULL_SELL.equals(response.getResponseType())) {
            Platform.runLater(this::sold);
        } else if (NO_ERROR.equals(response.getResponseType())) {
            Platform.runLater(this::bought);
        } else Platform.runLater(() -> viewMessage(((ResponseType) response.getResponseType()).getMessage()));
    }

    private void bought() {
        Placeable cardToBuy = response.getCardToBuy();
        viewMessage("you bought \n" + cardToBuy.getName());
        shopFxmlController.fillPanes(cardToBuy, shopFxmlController.collectionPane, false);
        shopFxmlController.money.setText(String.valueOf(response.getMoney()));
    }

    private void sold() {
        viewMessage("you sold\n" + response.getCardToSell());
//        shopFxmlController.collectionPane.getChildren().remove(response.getPaneToRemove());
        shopFxmlController.money.setText(String.valueOf(response.getMoney()));
    }

    private void getShopCards() {
        for (Placeable c : response.getShopCards()) {
            shopFxmlController.fillPanes(c, shopFxmlController.pane, true);
        }
        Collection collection = response.getCollection();
        for (Placeable p : collection.getCollectionCards()) {
            System.out.println(p instanceof Card);
        }
        for (Placeable c : collection.getCollectionCards()) {
            shopFxmlController.fillPanes(c, shopFxmlController.collectionPane, false);
        }
    }

    private void handleLeaderboardResponse() {
        Platform.runLater(() -> LoginPageController.getController().showTable(Game.getInstance().getSortedAccounts()));
    }

    public void setCollectionController(CollectionFxmlController collectionController) {
        this.collectionController = collectionController;
    }

    public void setShopFxmlController(ShopFxmlController shopFxmlController) {
        this.shopFxmlController = shopFxmlController;
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

}
