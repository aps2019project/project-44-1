package models;

public enum ErrorType {
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
