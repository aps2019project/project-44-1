package view;

import models.ErrorType;

public class CollectionRequest extends Request {
    private View view = new View();

    @Override
    public RequestType getType() {
        if (command.matches("search\\s\\w+"))
            return RequestType.SEARCH_DECK;
        else if (command.matches("create deck\\s\\w+"))
            return RequestType.CREATE_DECK;
        else if (command.matches("delete deck\\s\\w+"))
            return RequestType.DELETE_DECK;
        else if (command.matches("add\\s\\w+\\sto\\sdeck\\s\\w+"))
            return RequestType.ADD_CARD_TO_DECK;
        else if (command.matches("remove\\s\\w+\\sfrom\\sdeck\\s\\w+"))
            return RequestType.REMOVE_CARD_FROM_DECK;
        else if (command.matches("validate\\sdeck\\s\\w+"))
            return RequestType.VALIDATE;
        else if (command.matches("select\\sdeck\\s\\w+"))
            return RequestType.SELECT_DECK;
        else if (command.matches("show\\sdeck\\s\\w+"))
            return RequestType.SHOW_DECK;
        else {
            switch (command) {
                case "help":
                    return RequestType.HELP;
                case "show all decks":
                    return RequestType.SHOW_ALL_DECKS;
                case "exit":
                    return RequestType.EXIT;
                case "save":
                    return RequestType.SAVE;
                case "show":
                    return RequestType.SHOW_COLLECTION_ITEMS;
                default:
                    view.printError(ErrorType.GENERAL_ERROR);
            }
        }
        return null;
    }
}