package client;

import Main.Main;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonStreamParser;
import controller.fxmlControllers.CollectionFxmlController;
import controller.fxmlControllers.LoginPageController;
import controller.fxmlControllers.MainMenuController;
import controller.logicController.AccountController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import models.Enums.ErrorType;
import models.Game;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static server.ResponseType.SUCCESSFUL_SIGN_IN;

public class ResponseHandler extends Thread {
    private static ResponseHandler RESPONSE_HANDLER = new ResponseHandler();
    private JsonStreamParser jsonStreamParser;
    private Gson gson = new Gson();
    private Response response;
    private CollectionFxmlController collectionController;
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
        }
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
                Platform.runLater(() -> collectionController.makeAlert("Error while adding cards to deck", response.getResponseType().getMessage(), Alert.AlertType.ERROR));
                break;
            case SUCCESSFULLY_MOVE_CARD_TO_DECK:
            case SUCCESSFULLY_REMOVE_CARD_FROM_DECK:
                Platform.runLater(() -> collectionController.updateDeckCards());
                break;
            case MAIN_DECK_SELECTED:
                Platform.runLater(() -> collectionController.makeAlert("new main deck selected", null, Alert.AlertType.INFORMATION));
                break;
            case ENTER_COLLECTION:
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
        switch (response.getResponseType()) {
            case ENTER_SHOP:
                Platform.runLater(this::initializeShop);
        }
    }

    private void handleLeaderboardResponse() {
        Platform.runLater(() -> LoginPageController.getController().showTable(Game.getInstance().getSortedAccounts()));
    }

    public void setCollectionController(CollectionFxmlController collectionController) {
        this.collectionController = collectionController;
    }

    private void initializeShop() {
//        ShopFxmlController controller = MainMenuController.getShopFxmlController();
//        controller.money.setText(String.valueOf(shopController.getShop().getAccount().getMoney()));
//        shopController.setShopFxmlController(this);
//        controller.back.setOnAction(actionEvent -> Game.getInstance().loadPage(controller.back, "/view/fxmls/mainMenu.fxml"));
//        controller.craftGraphics();
//        controller.search.setOnKeyPressed(actionEvent -> {
//            if (actionEvent.getCode() == KeyCode.ENTER)
//                controller.searchInShop();
//        });
    }

}
