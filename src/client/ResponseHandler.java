package client;

import Main.Main;
import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;
import javafx.application.Platform;
import server.Response;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;

public class ResponseHandler extends Thread {
    private JsonStreamParser jsonStreamParser;
    private Gson gson = new Gson();

    public ResponseHandler(InputStream inputStream) {
        this.jsonStreamParser = new JsonStreamParser(new BufferedReader(new InputStreamReader(inputStream)));
    }

    @Override
    public void run() {
        try {
            while (jsonStreamParser.hasNext()) {
                Response response = gson.fromJson(jsonStreamParser.next(), Response.class);
                new Thread(() -> handleResponse(response)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(Response response) {
        switch (response.getEnvironment()) {
            case LOGIN_PAGE:
                handleLoginPageResponse(response);
                break;
            case COLLECTION:
                handleCollectionResponse(response);
                break;
            case BATTLE:
                handleBattleResponse(response);
                break;
            case SHOP:
                handleShopResponse(response);
        }
    }

    private void handleLoginPageResponse(Response response) {
        switch (response.getResponseType()) {
            case DUPLICATE_USERNAME:
            case INVALID_USERNAME:
            case REQUESTED_ACCOUNT_IS_ONLINE:
            case INVALID_PASSWORD:
            case SUCCESSFUL_SIGN_UP:
                Platform.runLater(() -> Main.getLoginPageController().appearLabel(response.getResponseType().getMessage()));
                break;
            case SUCCESSFUL_SIGN_IN:
                Platform.runLater(() -> Main.getStage().getScene().setRoot(Main.getMainMenu()));
                break;
        }
    }

    private void handleCollectionResponse(Response response) {

    }

    private void handleBattleResponse(Response response) {

    }

    private void handleShopResponse(Response response) {

    }

}
