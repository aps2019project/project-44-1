package server;

import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;
import controller.logicController.GameController;
import models.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler extends Thread {
    private JsonStreamParser parser;
    private ResponseSender responseSender;
    private Gson gson = new Gson();
    private Socket currentSocket;
    private Request request;

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
                request = gson.fromJson(parser.next(), Request.class);
                new Thread(this::handleRequest).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleRequest() {
        switch (request.getEnvironment()) {
            case SHOP:
                handleShopRequest();
                break;
            case BATTLE:
                handleBattleRequest();
                break;
            case COLLECTION:
                handleCollectionRequest();
                break;
            case LOGIN_PAGE:
                handleLoginPageRequest(request);
                break;
            case LEADER_BOARD:
                handleLeaderboardRequest();
                break;
            case MAIN_MENU:
                handleMainMenuRequest();
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

    private void handleCollectionRequest() {

    }

    private void handleBattleRequest() {

    }

    private void handleShopRequest() {

    }

    private void handleLeaderboardRequest() {
        responseSender.sendResponse(new Response(Environment.LEADER_BOARD));
    }

    private void handleMainMenuRequest() {
        if (request.getRequestType() == RequestType.LOG_OUT) {
            Game.getInstance().getOnlineAccounts().remove(request.getUsername());
        }
    }

}
