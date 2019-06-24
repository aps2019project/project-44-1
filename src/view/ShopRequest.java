package view;

public class ShopRequest extends Request {

    @Override
    public RequestType getType() {
        if (command.matches("search\\s\\w+"))
            return RequestType.SEARCH_SHOP;
        else if (command.matches("search collection\\s\\w+"))
            return RequestType.SEARCH_CARD_IN_COLLECTION;
        else if (command.matches("buy\\s\\w+"))
            return RequestType.BUY;
        else if (command.matches("sell\\s\\w+"))
            return RequestType.SELL;
        else if (command.matches("help"))
            return RequestType.HELP;
        else if (command.matches("exit"))
            return RequestType.EXIT;
        else if (command.matches("show"))
            return RequestType.SHOW_SHOP_CARDS;
        else {
            return RequestType.INVALID_COMMAND;
        }
    }

    /**
     * @return null when ...
     */
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
        }
    }

    /**
     * @return null when ...
     */
    public int getCardID() {
        if (getType() == RequestType.SELL) {
            try {
                return Integer.parseInt(command.split(" ")[1]);
            }catch (NumberFormatException ex){
                return -1;
            }
        }
        return -1;
    }

}
