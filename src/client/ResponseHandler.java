package client;

import Main.Main;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonStreamParser;
import controller.fxmlControllers.CollectionFxmlController;
import controller.fxmlControllers.LoginPageController;
import controller.fxmlControllers.MainMenuController;
import controller.fxmlControllers.ShopFxmlController;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import models.Game;
import server.Response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static server.ResponseType.SUCCESSFUL_SIGN_IN;

public class ResponseHandler extends Thread {
    private static ResponseHandler RESPONSE_HANDLER = new ResponseHandler();
    private JsonStreamParser jsonStreamParser;
    private Gson gson = new Gson();
    private Response response;
    private CollectionFxmlController collectionController;

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
            Platform.runLater(() -> Main.getStage().getScene().setRoot(Main.getMainMenu()));
            Main.setToken(response.getAuthToken());
        } else
            Platform.runLater(() -> Main.getLoginPageController().appearLabel(response.getResponseType().getMessage()));
    }

    private void handleCollectionResponse() {
        switch (response.getResponseType()) {
            case CREATE_DECK_SUCCESSFULLY:
                Platform.runLater(() -> collectionController.decks.getItems().add(response.getDeckToAdd()));
                break;
            case SUCCESSFULLY_REMOVE_DECK:
                removeDeck(response.getDeckToRemove());
                break;
            case DUPLICATE_DECK:
                Platform.runLater(() -> collectionController.makeAlert("Error while making deck", "This name was used before!"));
                break;
            case MORE_THAN_ONE_HERO_ERROR:
            case MORE_THAN_20_NORMAL_CARD_ERROR:
            case MORE_THAN_ONE_ITEM_ERROR:
                Platform.runLater(() -> collectionController.makeAlert("Error while adding cards to deck", response.getResponseType().getMessage()));
                break;
            case SUCCESSFULLY_MOVE_CARD_TO_DECK:
                addCardToDeck();
                break;
            case SUCCESSFULLY_REMOVE_CARD_FROM_DECK:
                removeCardFromDeck();
                break;
            case MAIN_DECK_SELECTED:
                Platform.runLater(() -> collectionController.makeAlert("new main deck selected", null));
                break;
        }

    }

    private void removeCardFromDeck() {

    }

    private void addCardToDeck() {

    }

    private void removeDeck(String deckToRemove) {
        Platform.runLater(() -> {
            collectionController.decks.getItems().remove(deckToRemove);
            collectionController.deckCardsFlowPane.getChildren().clear();
            collectionController.decks.setValue(null);
        });

    }

    private void handleBattleResponse() {

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
        ShopFxmlController controller = MainMenuController.getShopFxmlController();
        controller.money.setText(String.valueOf(shopController.getShop().getAccount().getMoney()));
        shopController.setShopFxmlController(this);
        controller.back.setOnAction(actionEvent -> Game.getInstance().loadPage(controller.back, "/view/fxmls/mainMenu.fxml"));
        controller.craftGraphics();
        controller.search.setOnKeyPressed(actionEvent -> {
            if (actionEvent.getCode() == KeyCode.ENTER)
                controller.searchInShop();
        });
    }

}
