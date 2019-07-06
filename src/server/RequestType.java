package server;

public enum RequestType {
    SIGN_UP,
    SIGN_IN,
    ENTER_SHOP,
    LOG_OUT,
    BUY,
    SELL,
    SELECT_MAIN_DECK,
    CREATE_DECK,
    DELETE_CARD_FROM_DECK,
    ADD_CARD_TO_DECK,
    REMOVE_DECK,
    ENTER_BATTLE,
    SHOW_LEADER_BOARD,
    //close the client connection with server
    CLOSE_CONNECTION,

}
