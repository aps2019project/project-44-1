package server;

public enum RequestType {
    SIGN_UP,
    SIGN_IN,
    SAVE,
    ENTER_SHOP,
    LOG_OUT,
    BUY,
    SELL,
    SELECT_MAIN_DECK,
    CREATE_DECK,
    REMOVE_CARD_FROM_DECK,
    ADD_CARD_TO_DECK,
    REMOVE_DECK,
    ENTER_BATTLE,
    SHOW_LEADER_BOARD,
    ENTER_COLLECTION,
    //close the client connection with server
    CLOSE_CONNECTION,
    IMPORT_DECK,
    EXPORT_DECK,
    // Requests needed to start battle
    ENTER_WAIT_PAGE_FOR_SECOND_PLAYER,
    ENTER_FIRST_SINGLE_MODE,
    ENTER_SECOND_SINGLE_MODE,
    ENTER_THIRD_SINGLE_MODE,

}
