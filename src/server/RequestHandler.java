package server;

import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;
import controller.logicController.CollectionController;
import controller.logicController.AccountController;
import controller.logicController.GameController;
import models.Collection;
import models.Game;
import models.Placeable;
import models.Shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler extends Thread {
    private JsonStreamParser parser;
    private ResponseSender responseSender;
    private Gson gson = new Gson();
    private Socket currentSocket;

    public RequestHandler(Socket socket) {
        try {
            currentSocket = socket;
            parser = new JsonStreamParser(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            responseSender = new ResponseSender(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (parser.hasNext()) {
                Request request = gson.fromJson(parser.next(), Request.class);
                new Thread(() -> handleRequest(request)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(Request request) {
        switch (request.getEnvironment()) {
            case SHOP:
                handleShopRequest();
                break;
            case BATTLE:
                handleBattleRequest();
                break;
            case COLLECTION:
                handleCollectionRequest(request);
                break;
            case LOGIN_PAGE:
                handleLoginPageRequest(request);
                break;
            case LEADER_BOARD:
                handleLeaderboardRequest();
                break;
            case MAIN_MENU:
                handleMainMenuRequest(request);
        }
    }

    private void handleLoginPageRequest(Request request) {
        GameController controller = GameController.getInstance();
        switch (request.getRequestType()) {
            case SIGN_IN:
                controller.signIn(request.getUsername(), request.getPassword(), responseSender);
                break;
            case SIGN_UP:
                controller.signUp(request.getUsername(), request.getPassword(), responseSender);
                break;
            case CLOSE_CONNECTION:
                closeConnection(request);
        }
    }

    private void closeConnection(Request request) {
        try {
            this.interrupt();
            responseSender.closeBufferedWriter();
            currentSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Main.getSockets().remove(currentSocket);
            Main.removeFromOnlineAccounts(request.getOuthToken());
            System.out.println(request.getOuthToken());
        }

    }

    private void handleCollectionRequest(Request request) {
        Collection collection = Main.getOnlineAccounts().get(request.getOuthToken()).getCollection();
        CollectionController controller = new CollectionController(collection);
        switch (request.getRequestType()) {
            case CREATE_DECK:
                controller.createDeck(request.getDeckToAdd(), responseSender);
                break;
            case ADD_CARD_TO_DECK:
                controller.addCardToDeck(request, responseSender);
                break;
            case REMOVE_DECK:
                controller.deleteDeck(request, responseSender);
                break;
            case REMOVE_CARD_FROM_DECK:
                controller.removeCardFromDeck(request, responseSender);
                break;
            case SELECT_MAIN_DECK:
                controller.selectMainDeck(request, responseSender);
                break;
            case IMPORT_DECK:
                controller.importDeck(request.getImportedDeck(), responseSender);
                break;
            case EXPORT_DECK:
                controller.exportDeck(request.getExportedDeck(), responseSender);
                break;
            case GET_CARD:
                Placeable placeable = Shop.getInstance().getCard(request.getCardToBuy());
                Response response = new Response(Environment.COLLECTION);
                response.setResponseType(ResponseType.GET_CARD);
                response.setCardToBuy(placeable);
                responseSender.sendResponse(response);
                break;

        }

    }

    private void handleBattleRequest() {

    }

    private void handleShopRequest() {

    }

    private void handleLeaderboardRequest() {
        responseSender.sendResponse(new Response(Environment.LEADER_BOARD));
    }

    private void handleMainMenuRequest(Request request) {
        switch (request.getRequestType()) {
            case LOG_OUT:
                Game.getInstance().getOnlineAccounts().remove(request.getUsername());
                break;
            case SAVE:
                AccountController.getInstance().save();
                break;
            case ENTER_COLLECTION:
                sendCollection(request);
                break;
            case ENTER_BATTLE:
                enterBattle(request);
        }
    }

    private void enterBattle(Request request) {
        Response response = new Response(Environment.BATTLE);
        if (Main.getOnlineAccounts().get(request.getOuthToken()).isReadyToPlay())
            response.setResponseType(ResponseType.MAIN_DECK_IS_VALID);
        else
            response.setResponseType(ResponseType.MAIN_DECK_IS_NOT_VALID);
        response.setAccount(Main.getOnlineAccounts().get(request.getOuthToken()));
        responseSender.sendResponse(response);
    }

    private void sendCollection(Request request) {
        Response response = new Response(Environment.COLLECTION);
        response.setResponseType(ResponseType.ENTER_COLLECTION);
        response.setCollection(Main.getOnlineAccounts().get(request.getOuthToken()).getCollection());
        responseSender.sendResponse(response);
    }

}
