package controller.logicController;

import models.Game;
import server.Environment;
import server.Response;
import server.ResponseSender;
import server.ResponseType;

public class GameController {
    private static GameController gameController = new GameController();

    private GameController() {
    }

    public static GameController getInstance() {
        return gameController;
    }

    public void signIn(String username, String password, ResponseSender responseSender) {
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

    public void signUp(String username, String password, ResponseSender responseSender) {
        Response response = new Response(Environment.LOGIN_PAGE);
        if (Game.getInstance().isUsedUsername(username)) {
            response.setResponseType(ResponseType.DUPLICATE_USERNAME);
        } else {
            Game.getInstance().createAccount(username, password);
            response.setResponseType(ResponseType.SUCCESSFUL_SIGN_UP);
        }
        responseSender.sendResponse(response);
    }

}
