package view;

import models.Account;
import models.ErrorType;

public class AccountRequest extends Request {

    private Account secondPlayer;
    private View view = new View();

    public Account getSecondPlayer() {
        return secondPlayer;
    }

    public RequestType getType() {
        if (getCommand().contains("enter")) {
            switch (getCommand().split("\\s")[1]) {
                case "battle":
                    return RequestType.BATTLE;
                case "collection":
                    return RequestType.COLLECTION;
                case "shop":
                    return RequestType.SHOP;
                default:
                    view.printError(ErrorType.GENERAL_ERROR);
                    return null;
            }
        } else {
            switch (getCommand()) {
                case "help":
                    return RequestType.HELP;
                case "exit":
                    return RequestType.EXIT;
                case "save":
                    return RequestType.SAVE;
                case "logout":
                    return RequestType.LOGOUT;
                default:
                    view.printError(ErrorType.GENERAL_ERROR);
                    return null;
            }
        }
    }
}
