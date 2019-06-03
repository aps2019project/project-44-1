package models;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import view.RequestType;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ArtificialIntelligence {
    private String Hero;
    private String[] Spell;
    private String[] Minions;
    private String Item;
    private int Prize;
    private Gson gson = new Gson();

    public Account getAccount(int level) {
        JsonObject obj = getJsonObject();
        JsonArray array = (JsonArray) obj.get("SinglePlayerLevels");
        ArtificialIntelligence ai = null;
        switch (level) {
            case 1:
                ai = gson.fromJson(array.get(0), ArtificialIntelligence.class);
                break;
            case 2:
                ai = gson.fromJson(array.get(0), ArtificialIntelligence.class);

                break;
            case 3:
                ai = gson.fromJson(array.get(0), ArtificialIntelligence.class);
        }
        return realAI(ai);
    }

    private JsonObject getJsonObject() {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader("src\\models\\database.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return gson.fromJson(reader, JsonObject.class);
    }

    private Account realAI(ArtificialIntelligence ai) {
        Account account = new Account("Computer", "1234");
        Deck deck = getMainDeck(ai);
        account.getCollection().setMainDeck(deck);
        return account;
    }

    private Deck getMainDeck(ArtificialIntelligence ai) {
        Deck deck = new Deck("main");
        Shop shop = Shop.getInstance();
        deck.setHero((models.Hero) shop.getCard(ai.Hero));
        for (String s : ai.Spell) {
            deck.getDeckCards().add(shop.getCard(s));
        }
        for (String s : ai.Minions) {
            deck.getDeckCards().add(shop.getCard(s));
        }
        deck.setItem((models.Item) shop.getCard(ai.Item));
        return deck;
    }

    public RequestType getType(int level) {
        switch (level) {
            case 1:
                return RequestType.DEATH_MATCH;
            case 2:
                return RequestType.CAPTURE_FLAG1;
            case 3:
                return RequestType.CAPTURE_FLAG2;
        }
        return null;
    }

}