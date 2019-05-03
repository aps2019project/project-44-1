package models.Enums;

public enum ErrorType {
    USED_BEFORE_USERNAME("This username is taken. Try another"),
    INVALID_USERNAME("invalid username!!!"),
    INVALID_PASSWORD("invalid password!!!"),
    GENERAL_ERROR("invalid command!!!"),
    ERROR_WITHOUT_MESSAGE("empty command"),
    ACCOUNT_NOT_FOUND("account not found! please register first"),
    CARD_NOT_FOUND_IN_COLLECTION("card not found in collection"),
    DUPLICATE_DECK("creation failed, this deck already exists"),
    DECK_NOT_FOUND("deck not found in collection"),
    DUPLICATE_CARD("card already exists in deck"),
    FULL_DECK("deck is full"),
    HERO_SET_BEFORE("hero has been set before"),
    ITEM_SET_BEFORE("item has been before"),
    CARD_NOT_FOUND_IN_DECK("there is no card with this ID in deck !!!"),
    CARD_NOT_FOUND_IN_SHOP("there is no card with tish name in shop !!!"),
    NOT_ENOUGH_MONEY_TO_BUY_CARD("you don't have enough money to buy this card !!!"),
    MAX_ITEMS_IN_COLLECTION_REACHED("you can not buy this card because you have 3 items in your collection !!!"),
    NO_ERROR("NO error");


    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }
}
