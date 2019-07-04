package server;

import com.google.gson.Gson;
import com.google.gson.JsonStreamParser;
import models.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler extends Thread {
    private JsonStreamParser parser;
    private ResponseSender responseSender;
    private Gson gson;

    public RequestHandler(Socket socket) {
        try {
            parser = new JsonStreamParser(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            responseSender = new ResponseSender(socket.getOutputStream());
            this.gson = new Gson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (parser.hasNext()) {
            Request request = gson.fromJson(parser.next(), Request.class);
            new Thread(() -> handleRequest(request)).start();
        }
    }

    private void handleRequest(Request request) {
        switch (request.getEnvironment()) {
            case SHOP:
                handleShopRequest(request);
                break;
            case BATTLE:
                handleBattleRequest(request);
                break;
            case COLLECTION:
                handleCollectionRequest(request);
                break;
            case LOGIN_PAGE:
                handleLoginPageRequest(request);
        }
    }

    private void handleLoginPageRequest(Request request) {
        switch (request.getRequestType()) {
            case SIGN_IN:
                signIn(request.getUsername(), request.getPassword());
                break;
            case SIGN_UP:
                signUp(request.getUsername(), request.getPassword());
                break;
        }
    }

    private void signIn(String username, String password) {
        Response response = new Response(Environment.LOGIN_PAGE);
        if (!Game.getInstance().isUsedUsername(username))
            response.setResponseType(ResponseType.INVALID_USERNAME);
        else {
            if (Game.getInstance().isOnline(username))
                response.setResponseType(ResponseType.REQUESTED_ACCOUNT_IS_ONLINE);
            else if (!Game.getInstance().isValidPassword(Game.getAccount(username), password))
                response.setResponseType(ResponseType.INVALID_PASSWORD);
            else {
                response.setResponseType(ResponseType.SUCCESSFUL_SIGN_IN);
                Game.getInstance().addToOnlineAccounts(Game.getAccount(username));
            }
        }
        responseSender.sendResponse(response);
    }

    private void signUp(String username, String password) {
        Response response = new Response(Environment.LOGIN_PAGE);
        if (Game.getInstance().isUsedUsername(username)) {
            response.setResponseType(ResponseType.DUPLICATE_USERNAME);
        } else {
            Game.getInstance().createAccount(username, password);
            response.setResponseType(ResponseType.SUCCESSFUL_SIGN_UP);
        }
        responseSender.sendResponse(response);
    }

    private void handleCollectionRequest(Request request) {

    }

    private void handleBattleRequest(Request request) {

    }

    private void handleShopRequest(Request request) {

    }
}
