package models;

public enum ErrorType {
    // TODO: 14/04/2019 these are tests
    USED_BEFORE_USERNAME("This username is taken. Try another"),
    INVALID_USERNAME("invalid username"),
    INVALID_PASSWORD("invalid password"),
    ERROR_WITHOUT_MESSAGE("empty command");


    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }
}
