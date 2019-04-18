package models;

public enum ErrorType {
    USED_BEFORE_USERNAME("This username is taken. Try another"),
    INVALID_USERNAME("invalid username"),
    INVALID_PASSWORD("invalid password"),
    GENERAL_ERROR("invalid command"),
    ERROR_WITHOUT_MESSAGE("empty command"),
    ACCOUNT_NOT_FOUND("account not found! please register first");


    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }
}
