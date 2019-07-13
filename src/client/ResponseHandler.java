package client;

import Main.Main;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonStreamParser;
import controller.fxmlControllers.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import models.Placeable;
import server.Response;
import view.fxmls.wrapperClasses.CardContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import static models.Enums.ErrorType.ALL_CARDS_HAVE_BEEN_SOLD;
import static models.Enums.ErrorType.NO_ERROR;
import static server.ResponseType.SUCCESSFUL_SIGN_IN;

public class ResponseHandler extends Thread {
    private static ResponseHandler RESPONSE_HANDLER = new ResponseHandler();
    private JsonStreamParser jsonStreamParser;
    private Gson gson = new Gson();
    private Response response;
    private CollectionFxmlController collectionController;
    private ShopFxmlController shopFxmlController;
    private MainMenuController mainMenuController;
    private MapController mapController;
    private MultiMapController multiMapController;

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
                new Thread(() -> handleResponse(response)).start();
            }
        } catch (JsonIOException ignored) {

        }
    }

    private void handleResponse(Response response) {
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
                handleShopResponse(response);
                break;
            case LEADER_BOARD:
                handleLeaderboardResponse();
                break;
            case MAIN_MENU:
                handleMainMenu();
                break;
            case MAP:
                handleMap();
        }
    }

    private void handleMainMenu() {
        switch (response.getResponseType()) {
            case SHOW_MATCH_HISTORY:
                MatchHistoriesController.setAccount(response.getAccount());
                showMatchHistory();
                break;
            case LOG_OUT:
                logout();
                break;
            case ENTER_SHOP:
                loadShop();
        }
    }

    private void loadShop() {
        ShopFxmlController.setAccount(response.getAccount());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/shop.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
            shopFxmlController = loader.getController();
            CardContainer.setShopFxmlController(shopFxmlController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logout() {
        Main.setToken(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/loginPage.fxml"));
        try {
            Main.getStage().getScene().setRoot(loader.load());
            Main.setLoginPageController(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
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
                try {
                    Main.getStage().getScene().setRoot(loader.load());
                    mainMenuController = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //============================================================COLLECTION
    private void handleCollectionResponse() {
        if (response.getCollection() != null)
            CollectionFxmlController.setCollection(response.getCollection());
        switch (response.getResponseType()) {
            case CREATE_DECK_SUCCESSFULLY:
                Platform.runLater(() -> collectionController.decks.getItems().add(response.getDeckToAdd()));
                break;
            case SUCCESSFULLY_REMOVE_DECK:
                removeDeck(response.getDeckToRemove());
                break;
            case DUPLICATE_DECK:
                Platform.runLater(() -> collectionController.makeAlert("Error while making deck", "This name was used before!", Alert.AlertType.ERROR));
                break;
            case MORE_THAN_ONE_HERO_ERROR:
            case MORE_THAN_20_NORMAL_CARD_ERROR:
            case MORE_THAN_ONE_ITEM_ERROR:
                Platform.runLater(() -> collectionController.makeAlert("Error while adding cards to deck",
                        response.getResponseType().getMessage(), Alert.AlertType.ERROR));
                break;
            case SUCCESSFULLY_MOVE_CARD_TO_DECK:
            case SUCCESSFULLY_REMOVE_CARD_FROM_DECK:
                Platform.runLater(() -> collectionController.updateDeckCards());
                break;
            case MAIN_DECK_SELECTED:
                Platform.runLater(() -> collectionController.makeAlert("new main deck selected", null,
                        Alert.AlertType.INFORMATION));
                break;
            case ENTER_COLLECTION:
                loadCollection();
                break;
            case EXPORT_DECK:
                Platform.runLater(() -> collectionController.makeAlert("deck exported", response.getResponseType().getMessage(),
                        Alert.AlertType.INFORMATION));
                break;
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
                    collectionController = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    //=============================================================BATTLE
    private void handleBattleResponse() {
        switch (response.getResponseType()) {
            case MAIN_DECK_IS_VALID:
                loadBattleMenu();
                break;
            case MAIN_DECK_IS_NOT_VALID:
                Platform.runLater(() -> mainMenuController.appearLabel(response.getResponseType().getMessage()));
                break;
            case ENTER_MAP:
                Platform.runLater(() -> MainMenuController.loadPage("/view/fxmls/map.fxml"));
                break;
            case ENTER_MULTI_BATTEL_MAP:
                MultiMapController.setPlayer(response.getPlayer());
                MultiMapController.setLogicMap(response.getMap());
                MultiMapController.setSecondPlayerName(response.getSecondPlayerName());
                loadBattleMap();
                break;
            case MOVE_CARD:
                MultiMapController.setLogicMap(response.getMap());
                multiMapController.updateMap();
        }
    }

    private void loadBattleMap() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmls/multiMap.fxml"));
                try {
                    Main.getStage().getScene().setRoot(loader.load());
                    multiMapController = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadBattleMenu() {
        Platform.runLater(() -> MainMenuController.loadPage("/view/fxmls/battleMenu.fxml"));
    }

    private void handleMap() {
        switch (response.getResponseType()) {
            case CHAT:
                Platform.runLater(this::craftMessageBox);
        }
    }

    private void craftMessageBox() {
        HBox hBox = new HBox();
        if (response.getSender().equals("-1"))
            hBox.getChildren().addAll(new Label(response.getMessage()), new Label("YOU"));
        else hBox.getChildren().addAll(new Label(response.getSender()), new Label(response.getMessage()));
        mapController.chatBox.getChildren().add(hBox);
    }

    //=============================================================SHOP
    private void handleShopResponse(Response response) {
        switch (response.getResponseType()) {
            case BUY_CARD:
                if (response.getShopErrorType().equals(ALL_CARDS_HAVE_BEEN_SOLD)) {
                    Platform.runLater(() -> viewMessage(ALL_CARDS_HAVE_BEEN_SOLD.getMessage()));
                    return;
                }
                Platform.runLater(this::bought);
                Platform.runLater(() -> shopFxmlController.money.setText(response.getMoney()));
                break;
            case SEARCH_IN_SHOP:
                Platform.runLater(() -> shopFxmlController.shop.setVvalue(response.getvValue()));
                break;
            case SUCCESSFUL_SELL:
                Platform.runLater(() -> sold(response));
                Platform.runLater(() -> shopFxmlController.money.setText(response.getMoney()));
                break;
            case REMOVE_AUCTION_CARD_FROM_COLLECTION:
                Platform.runLater(() -> removeCardFromShopCollection(response));
                break;
            case SUCCESSFUL_BUY_AUCTION:
                Platform.runLater(this::bought);
                Platform.runLater(() -> shopFxmlController.money.setText(response.getMoney()));
                break;
            case SUCCESSFUL_SELL_AUCTION:
                viewMessage("you sold\n" + response.getCardToSell());
                break;
            case UPDATE_ACCOUNT_MONEY:
                Platform.runLater(() -> shopFxmlController.money.setText(response.getMoney()));
                break;
            case UPDATE_AUCTION_CARD_PRICE:
                Platform.runLater(() -> shopFxmlController.getAuctionCard(response.getAuctionCardId()).updateAuctionCard(response.getSuggestedPrice(), response.getNewBuyer()));
                break;
            case NEW_AUCTION_CARD_ADDED_TO_SHOP:
                Platform.runLater(() -> shopFxmlController.addToAuctionCards(response.getAuctionCard(), response.getAuctionCardId(),response.getRemainedTimeOfAuction()));
            default:
                if (response.getShopErrorType() != null && !response.getShopErrorType().equals(NO_ERROR))
                    Platform.runLater(() -> viewMessage(response.getShopErrorType().getMessage()));
                break;
        }
    }

    private void bought() {
        Placeable cardToBuy = response.getCardToBuy();
        viewMessage("you bought \n" + cardToBuy.getName());
        shopFxmlController.fillPanes(cardToBuy.getName(), shopFxmlController.collectionPane, false);
        shopFxmlController.money.setText(String.valueOf(response.getMoney()));
    }

    private void sold(Response response) {
        viewMessage("you sold\n" + response.getCardToSell());
        removeCardFromShopCollection(response);
    }

    private void removeCardFromShopCollection(Response response) {
        for (Node n : shopFxmlController.collectionPane.getChildren()) {
            if (n.getId().equals(response.getPaneToRemoveID())) {
                shopFxmlController.collectionPane.getChildren().remove(n);
                break;
            }
        }
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

    //=============================================================
    private void handleLeaderboardResponse() {
        LeaderboardController.setAccounts(response.getAccounts());
        LeaderboardController.setOnlineAccounts(response.getOnlineAccounts());
        Platform.runLater(() -> {
            try {
                Main.getStage().getScene().setRoot(new FXMLLoader(getClass().
                        getResource("/view/fxmls/leaderboard.fxml")).load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setCollectionController(CollectionFxmlController collectionController) {
        this.collectionController = collectionController;
    }

    public void setShopFxmlController(ShopFxmlController shopFxmlController) {
        this.shopFxmlController = shopFxmlController;
    }

    public MapController getMapController() {
        return mapController;
    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
    }

}
