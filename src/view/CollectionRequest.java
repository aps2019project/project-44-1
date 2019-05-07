package view;

import models.Enums.ErrorType;

public class CollectionRequest extends Request {
    private View view = View.getInstance();

    @Override
    public RequestType getType() {
        if (command.matches("search \\w+"))
            return RequestType.SEARCH_CARD_IN_COLLECTION;
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
            view.printError(ErrorType.INVALID_COMMAND);
            return RequestType.INVALID_COMMAND;
        }
    }

    public String getDeckName() {
        switch (getType()) {
            case CREATE_DECK:
                return command.split(" ")[2];
            case DELETE_DECK:
                return command.split(" ")[2];
            case ADD_CARD_TO_DECK:
                return command.split(" ")[4];
            case REMOVE_CARD_FROM_DECK:
                return command.split(" ")[4];
            case SELECT_DECK:
                return command.split(" ")[2];
            case SHOW_DECK:
                return command.split(" ")[2];
            case VALIDATE:
                return command.split(" ")[2];
            default:
                return null;
            /** return null when ... */
        }
    }

    public int getCardID() {
        switch (getType()) {
            case ADD_CARD_TO_DECK:
                return Integer.parseInt(command.split(" ")[1]);
            case REMOVE_CARD_FROM_DECK:
                return Integer.parseInt(command.split(" ")[1]);
            default:
                return -1;
            /** return -1 when ... */
        }
    }

    public String getCardName() {
        if (getType() == RequestType.SEARCH_CARD_IN_COLLECTION) {
            return command.split(" ")[1];
        }
        return null;
        /** return null when ... */
    }

}