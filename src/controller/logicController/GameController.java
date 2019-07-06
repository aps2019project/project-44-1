package controller.logicController;

import models.Game;
import server.*;

import java.time.LocalDateTime;
import java.util.Date;

public class GameController {
    private static GameController gameController = new GameController();

    private GameController() {
    }

    public static GameController getInstance() {
        return gameController;
    }

    public void signIn(String username, String password, ResponseSender responseSender) {
        Game game = Game.getInstance();
        Response response = new Response(Environment.LOGIN_PAGE);
        if (!game.isUsedUsername(username))
            response.setResponseType(ResponseType.INVALID_USERNAME);
        else {
            if (game.isOnline(username))
                response.setResponseType(ResponseType.REQUESTED_ACCOUNT_IS_ONLINE);
            else if (!game.isValidPassword(Game.getAccount(username), password))
                response.setResponseType(ResponseType.INVALID_PASSWORD);
            else {
                response.setResponseType(ResponseType.SUCCESSFUL_SIGN_IN);
                //make auth token
                String token = org.apache.commons.codec.digest.DigestUtils.sha256Hex(username + password + LocalDateTime.now().toString());
                response.setAuthToken(token);
                Main.addToOnlineAccounts(token,Game.getAccount(username));
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
