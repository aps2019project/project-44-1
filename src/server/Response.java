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
}
