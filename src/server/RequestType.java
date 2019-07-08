package server;

public enum RequestType {
    SIGN_UP,
    SIGN_IN,
    SAVE,
    LOG_OUT,
    BUY,
    SELL,
    SELECT_MAIN_DECK,
    CREATE_DECK,
    REMOVE_CARD_FROM_DECK,
    ADD_CARD_TO_DECK,
    REMOVE_DECK,
    GET_SHOP_CARDS,
    ENTER_BATTLE,
    SEARCH_IN_SHOP,
    SHOW_LEADER_BOARD,
    ENTER_COLLECTION,
    ACCOUNT_MONEY,
    //close the client connection with server
    CLOSE_CONNECTION,
    IMPORT_DECK,
    EXPORT_DECK,
    GET_CARD

}
