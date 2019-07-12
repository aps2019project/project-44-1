package server;

public enum ResponseType {

    //sign up responses
    DUPLICATE_USERNAME("This username is taken. Try another"),
    SUCCESSFUL_SIGN_UP("Signed up successfully"),
    //sign in responses
    INVALID_USERNAME("Invalid username!!!"),
    INVALID_PASSWORD("Invalid password!!!"),
    REQUESTED_ACCOUNT_IS_ONLINE("This account is online now. Try another!!!"),
    SUCCESSFUL_SIGN_IN("Signed in successfully"),
    //collection responses
    ENTER_COLLECTION("enter collection"),
    CREATE_DECK_SUCCESSFULLY("New deck created Successfully"),
    DUPLICATE_DECK("creation failed, this deck already exists"),
    MORE_THAN_ONE_HERO_ERROR("can not add more than one hero to deck"),
    MORE_THAN_ONE_ITEM_ERROR("can not add more than one item to deck"),
    MORE_THAN_20_NORMAL_CARD_ERROR("can not add more than 20 minion and spell to deck"),
    SUCCESSFULLY_MOVE_CARD_TO_DECK("Cards added to deck successfully"),
    SUCCESSFULLY_REMOVE_DECK("Selected deck deleted successfully"),
    SUCCESSFULLY_REMOVE_CARD_FROM_DECK("Selected cards removed successfully"),
    MAIN_DECK_SELECTED("Main deck changed"),
    GET_CARD("card is ready to get"),
    //shop responses
    ENTER_SHOP("enter shop"),
    ENTER_MAP(""),
    ENTER_MULTI_BATTEL_MAP(""),
    BUY_CARD(""),
    NOT_FOUND_CARD(""),
    SEARCH_IN_SHOP(""),
    SUCCESSFUL_SELL("you bought\n"),
    UNSUCCESSFUL_BUY(""),
    SINGLE_PLAYER(""),
    //battle responses
    MAIN_DECK_IS_NOT_VALID("selected deck is invalid"),
    MAIN_DECK_IS_VALID("choose single player or multi player"),
    ENTER_WAIT_PAGE(""),
    //main menu responses
    SHOW_MATCH_HISTORY("show match history"),
    LOG_OUT(""),
    CHAT(""),
    EXPORT_DECK(""),
    MOVE_CARD(""),
    REMOVE_AUCTION_CARD_FROM_COLLECTION(""),
    NEW_AUCTION_CARD_ADDED_TO_SHOP(""),
    SUCCESSFUL_SELL_AUCTION(""),
    SUCCESSFUL_BUY_AUCTION(""),



    ;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResponseType(String message) {
        this.message = message;
    }

}
