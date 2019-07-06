package server;

import java.util.ArrayList;

public class Response {
    private ResponseType responseType;
    private Environment environment;
    private ArrayList<String> cardsToSell;
    private ArrayList<String> cardsToBuy;
    private String mainDeck;
    private String deckToRemove;
    private String deckToRemoveCardFrom;
    private ArrayList<String> cardsToRemoveFromDeck;
    private String authToken;

    public Response(Environment environment) {
        this.environment = environment;
    }

    public ResponseType getResponseType() {
        return responseType;
    }


    public Environment getEnvironment() {
        return environment;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
