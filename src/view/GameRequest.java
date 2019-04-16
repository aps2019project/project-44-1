package view;

import models.ErrorType;

public class GameRequest extends Request {

    private GameRequestType gameRequestType;
    //private GameView gameView = new GameView();


    public GameRequestType getGameRequestType() {
        return gameRequestType;
    }

    public GameRequest() {
        setNewCommand();
        this.gameRequestType = getType(command);
    }

    private GameRequestType getType(String command) {
        if (command.matches("create account \\w+"))
            return GameRequestType.CREATE_ACCOUNT;
        else if (command.matches("login \\w+"))
            return GameRequestType.LOGIN;
        //else gameView.print(ErrorType.GENERAL_ERROR);
        return null;
    }
}
