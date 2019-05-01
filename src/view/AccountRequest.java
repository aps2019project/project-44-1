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
        switch (command) {
            case "enter battle":
                return RequestType.ENTER_BATTLE;
            case "enter collection":
                return RequestType.ENTER_COLLECTION;
            case "enter shop":
                return RequestType.ENTER_SHOP;
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
                return RequestType.INVALID_COMMAND;
        }
    }
}
