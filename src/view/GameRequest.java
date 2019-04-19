package view;

import models.ErrorType;

public class GameRequest extends Request {

    @Override
    public RequestType getType() {
        View view = new View();
        if (command.matches("create account \\w+"))
            return RequestType.CREATE_ACCOUNT;
        else if (command.matches("login \\w+"))
            return RequestType.LOGIN;
        else {
            switch (command) {
                case "show leaderboard":
                    return RequestType.SHOW_LEADERBOARD;
                case "help":
                    return RequestType.HELP;
                case "exit":
                    return RequestType.EXIT;
                default:view.printError(ErrorType.GENERAL_ERROR);
            }
        }
        return null;
    }
}
