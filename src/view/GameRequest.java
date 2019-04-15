package view;

import controller.GameController;
import models.RequestType;

public class GameRequest extends Request {
    private static final String SIGN_IN = "Enter battle";
    private static final String SIGN_UP = "Enter collection";
    private static final String SHOW_LEADERBOARD = "Show leaderboard";

    @Override
    public RequestType getType() {
        switch (command) {
            case SIGN_IN:
                return RequestType.LOGIN;
            case SIGN_UP:
                return RequestType.SIGN_UP;
            case SHOW_LEADERBOARD:
                return RequestType.SHOW_LEADERBOARD;
            case HELP:
                return RequestType.HELP;
            case EXIT:
                return RequestType.EXIT;
            default:
                // TODO: 15/04/2019 what to do?
                return null;
        }
    }

    public String getUserName() {
        switch (getType()) {
            case LOGIN:
                return command.split("\\s")[1];
            case SIGN_UP:
                return command.split("\\s")[1];
            default:
                // TODO: 15/04/2019 what to do?
                return null;
        }
    }

    public String getPassword(GameView view){
        view.printGetPasswordCommand();
        getNewCommand();
        return command.trim();
        // trim : delete spaces from password
    }
}
