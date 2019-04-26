package models;

public enum ErrorType {
    USED_BEFORE_USERNAME("This username is taken. Try another"),
    INVALID_USERNAME("invalid username"),
    INVALID_PASSWORD("invalid password"),
    GENERAL_ERROR("invalid command"),
    ERROR_WITHOUT_MESSAGE("empty command"),
    ACCOUNT_NOT_FOUND("account not found! please register first"),
    CARD_NOT_FOUND("card not found in collection"),
    DUPLICATE_DECK("creation failed, this deck already exists"),
    DECK_NOT_FOUND("deck not found in collection"),
    DUPLICATE_CARD("card already exists in deck"),
    FULL_DECK("deck is full"),
    HERO_SET_BEFORE("hero has been set before"),
    ITEM_SET_BEFORE("item has been before");


    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }
}
