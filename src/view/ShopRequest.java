package view;

import models.ErrorType;

public class ShopRequest extends Request {

    @Override
    public RequestType getType() {
        View view = new View();
        if (command.matches("search\\s\\w+"))
            return RequestType.SEARCH_SHOP;
        else if (command.matches("search collection\\s\\w+"))
            return RequestType.SEARCH_COLLECTION;
        else if (command.matches("buy\\s\\w+"))
            return RequestType.BUY;
        else if (command.matches("sell\\s\\w+"))
            return RequestType.SELL;
        else {
            switch (command) {
                case "help":
                    return RequestType.HELP;
                case "exit":
                    return RequestType.EXIT;
                case "show":
                    return RequestType.SHOW_SHOP_CARDS;
                default:
                    view.printError(ErrorType.GENERAL_ERROR);
            }

        }
        return null;
    }

    public String getCardName() {
        switch (getType()) {
            case SEARCH_CARD_IN_COLLECTION:
                return command.split(" ")[2];
            case SEARCH_SHOP:
                return command.split(" ")[1];
            case BUY:
                return command.split(" ")[1];
            default:
                return null;
            /** return null when ... */
        }
    }

    public int getCardID() {
        if (getType() == RequestType.SELL) {
            return Integer.parseInt(command.split(" ")[1]);
        }
        return -1;
        /** return -1 when ... */

    }

}