package server;

public enum ResponseType {

    //sign up responses
    DUPLICATE_USERNAME("This username is taken. Try another"),
    SUCCESSFUL_SIGN_UP("Signed up successfully"),
    //sign in responses
    INVALID_USERNAME("Invalid username!!!"),
    INVALID_PASSWORD("Invalid password!!!"),
    REQUESTED_ACCOUNT_IS_ONLINE("This account is online now. Try another!!!"),
    SUCCESSFUL_SIGN_IN("Signed in successfully")


    ;
    private String message;

    public String getMessage() {
        return message;
    }

    ResponseType(String message) {
        this.message = message;
    }

}
