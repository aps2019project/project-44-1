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
import models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

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
                break;
            case SHOW_LEADER_BOARD:
                showLeaderBoard();
                break;
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

    private void handleBattleRequest(Request request) {
        switch (request.getRequestType()) {
            case ENTER_WAIT_PAGE_FOR_SECOND_PLAYER:
                Response response = new Response(Environment.BATTLE);
                response.setResponseType(ResponseType.ENTER_WAIT_PAGE);
                responseSender.sendResponse(response);
                OpponentFinder.addToWaitingAccounts(Main.getOnlineAccounts().get(request.getOuthToken()),responseSender);

        }
    }

    private void handleShopRequest(Request request) {
        Response response = new Response(Environment.SHOP);
        Shop shop = Shop.getInstance();
        Account account = Main.getOnlineAccounts().get(request.getOuthToken());
        switch (request.getRequestType()) {
            case ACCOUNT_MONEY:
                response.setMoney(account.getMoney());
                response.setResponseType(ResponseType.ACCOUNT_MONEY);
                break;
            case SEARCH_IN_SHOP:
                searchInShop(request, response, shop);
                break;
            case GET_SHOP_CARDS:
                getShopCards(request, response, shop);
                break;
            case BUY:
                String cardName = request.getCardToBuy().split("\n")[0];
                response.setShopErrorType(shop.buy(cardName));
                response.setMoney(account.getMoney());
                response.setCardToBuy(shop.getCard(cardName));
                break;
            case SELL:
                sell(request, response, shop, account);
        }
        responseSender.sendResponse(response);
    }

    private void sell(Request request, Response response, Shop shop, Account account) {
        String cardName = request.getCardToSell().split("\n")[0];
        if (shop.sell(account.getCollection().getCardIDInCollection(cardName))) {
            response.setResponseType(ResponseType.SUCCESSFULL_SELL);
            response.setMoney(account.getMoney());
            response.setPaneToRemoveID(request.getPaneToSellID());
            response.setCardToSell(cardName);
        }
    }

    private void getShopCards(Request request, Response response, Shop instance) {
        response.setShopCards(instance.getCards());
        response.setResponseType(ResponseType.GET_SHOP_CARDS);
        response.setCollection(Main.getOnlineAccounts().get(request.getOuthToken()).getCollection());
        responseSender.sendResponse(response);
    }

    private void searchInShop(Request request, Response response, Shop instance) {
        Placeable p = instance.getCard(request.getSearchedString());
        if (p != null) {
            ArrayList<Placeable> cards = instance.getCards();
            double x = (double) (cards.indexOf(p) + 1) / cards.size();
            response.setResponseType(ResponseType.SEARCH_IN_SHOP);
            response.setvValue(x);
        }
    }

    private void showLeaderBoard() {
        Response response = new Response(Environment.LEADER_BOARD);
        response.setAccounts(Game.getInstance().getSortedAccounts());
        response.setOnlineAccounts(Main.getOnlineAccounts());
        responseSender.sendResponse(response);
    }

    private void handleMainMenuRequest(Request request) {
        switch (request.getRequestType()) {
            case LOG_OUT:
                logout(request);
                break;
            case SAVE:
                AccountController.getInstance().save();
                break;
            case ENTER_COLLECTION:
                sendCollection(request);
                break;
            case ENTER_BATTLE:
                enterBattle(request);
                break;
            case SHOW_MATCH_HISTORY:
                showMatchHistory(request);
                break;
        }
    }

    private void logout(Request request) {
        Response response = new Response(Environment.MAIN_MENU);
        response.setResponseType(ResponseType.LOG_OUT);
        Main.removeFromOnlineAccounts(request.getOuthToken());
        responseSender.sendResponse(response);
    }

    private void showMatchHistory(Request request) {
        Response response = new Response(Environment.MAIN_MENU);
        response.setResponseType(ResponseType.SHOW_MATCH_HISTORY);
        response.setAccount(Main.getOnlineAccounts().get(request.getOuthToken()));
        responseSender.sendResponse(response);

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

