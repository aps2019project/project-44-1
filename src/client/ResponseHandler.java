package client;

import Main.Main;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonStreamParser;
import controller.fxmlControllers.CollectionFxmlController;
import controller.fxmlControllers.LoginPageController;
import controller.logicController.CollectionController;
import controller.logicController.AccountController;
import javafx.application.Platform;
import models.Game;
import server.Response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static server.ResponseType.SUCCESSFUL_SIGN_IN;

public class ResponseHandler extends Thread {
    private JsonStreamParser jsonStreamParser;
    private Gson gson = new Gson();
    private Response response;

    public ResponseHandler(InputStream inputStream) {
        this.jsonStreamParser = new JsonStreamParser(new BufferedReader(new InputStreamReader(inputStream)));
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
        switch (response.getResponseType()){
            case CREATE_DECK_SUCCESSFULLY:
                createNewDeck();
                break;
        }

    }

    private void createNewDeck() {

    }

    private void handleBattleResponse() {

    }

    private void handleShopResponse() {

    }

    private void handleLeaderboardResponse() {
        Platform.runLater(() -> LoginPageController.getController().showTable(Game.getInstance().getSortedAccounts()));
    }

}
