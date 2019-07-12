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
    ENTER_BATTLE,
    SHOW_LEADER_BOARD,
    ENTER_COLLECTION,
    SINGLE_PLAYER,
    //close the client connection with server
    CLOSE_CONNECTION,
    IMPORT_DECK,
    EXPORT_DECK,
    GET_CARD,
    MULTI_PLAYER,
    REGRETED,
    // Requests needed to start battle
    WAIT_FOR_SECOND_PLAYER,
    SHOW_MATCH_HISTORY,
    SEARCH_IN_SHOP,
    ACCOUNT_MONEY,
    GET_SHOP_CARDS,
    ENTER_SHOP,
    CHAT,
    MOVE_CARD,
    CHEAT,
    SUGGEST_NEW_COST,
    AUCTION_CARD,
    ;

}
