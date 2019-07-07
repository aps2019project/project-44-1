package models.Enums;

public enum ErrorType {
    INVALID_COMMAND("invalid command!!!"),
    ERROR_WITHOUT_MESSAGE("empty command"),
    CARD_NOT_FOUND_IN_COLLECTION("card not found in collection"),
    DECK_NOT_FOUND("deck not found in collection"),
    FULL_DECK("deck is full"),
    HERO_SET_BEFORE("hero has been set before"),
    ITEM_SET_BEFORE("item has been before"),
    CARD_NOT_FOUND_IN_DECK("there is no card with this ID in deck !!!"),
    NOT_ENOUGH_MONEY_TO_BUY_CARD("you don't \nhave enough\n money to buy\n this card !!!"),
    MAX_ITEMS_IN_COLLECTION_REACHED("you can't \nbuy this card\n because you have\n 3 items in your\n collection !!!"),
    NO_ERROR("NO error"),
    MAIN_DECK_IS_NOT_VALID("selected deck is invalid"),
    CARD_NOT_FOUND_IN_BATTLE("there is no card with tish name in game map !!!"),
    INVALID_CARD_NAME("Invalid card name"),
    NO_ENOUGH_MANA("you don't have enough mana"),
    INVALID_TARGET("invalid target"),
    INVALID_CARD_ID("invalid card ID"),
    NO_CARD_SELECTED("no card has been selected"),
    DEST_IS_UNAVAILABLE_FOR_ATTACK("opponent minion is unavailable for attack"),
    INVALID_DEST_ID("Invalid card id"),
    CARD_CANT_MOVE("this card has been moved this turn"),
    ALL_CARDS_HAVE_BEEN_SOLD("all of this type have been sold!!!");

    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }

}
