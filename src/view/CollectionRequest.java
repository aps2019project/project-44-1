package view;

import models.ErrorType;

public class CollectionRequest extends Request {
    private View view = new View();

    @Override
    public RequestType getType() {
        if (command.matches("search \\w+"))
            return RequestType.SEARCH_DECK;
        else if (command.matches("create deck \\w+"))
            return RequestType.CREATE_DECK;
        else if (command.matches("delete deck \\w+"))
            return RequestType.DELETE_DECK;
        else if (command.matches("add \\w+ to deck \\w+"))
            return RequestType.ADD_CARD_TO_DECK;
        else if (command.matches("remove \\w+ from deck \\w+"))
            return RequestType.REMOVE_CARD_FROM_DECK;
        else if (command.matches("validate deck \\w+"))
            return RequestType.VALIDATE;
        else if (command.matches("select deck \\w+"))
            return RequestType.SELECT_DECK;
        else if (command.matches("show deck \\w+"))
            return RequestType.SHOW_DECK;
        else if (command.matches("help"))
            return RequestType.HELP;
        else if (command.matches("show all decks"))
            return RequestType.SHOW_ALL_DECKS;
        else if (command.matches("exit"))
            return RequestType.EXIT;
        else if (command.matches("save"))
            return RequestType.SAVE;
        else if (command.matches("show"))
            return RequestType.SHOW_COLLECTION_ITEMS_AND_CARDS;
        else {
            view.printError(ErrorType.GENERAL_ERROR);
            return RequestType.INVALID_COMMAND;
        }
    }
}