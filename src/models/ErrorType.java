package models;

public enum ErrorType {
    // TODO: 14/04/2019 these are tests
    INVALID_USERNAME("invalid username"),
    INVALID_PASSWORD("invalid password");


    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }
}
