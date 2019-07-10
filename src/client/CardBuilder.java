package client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import models.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CardBuilder {
    private ArrayList<Placeable> cards;
    {
        Gson gson = new Gson();
        JsonReader reader = null;
        Hero[] hs = null;
        Minion[] ms = null;
        try {
            reader = new JsonReader(new FileReader("src\\models\\database.json"));
            hs = gson.fromJson(new JsonReader(new FileReader("src\\models\\customHeroes.json")),
                    Hero[].class);
            ms = gson.fromJson(new JsonReader(new FileReader("src\\models\\customMinions.json")),
                    Minion[].class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonObject obj = gson.fromJson(reader, JsonObject.class);
        Hero[] heroes = gson.fromJson(obj.get("Hero"), Hero[].class);
        Minion[] minions = gson.fromJson(obj.get("minions"), Minion[].class);
        Item[] items = gson.fromJson(obj.get("Items"), Item[].class);
        Spell[] spells = gson.fromJson(obj.get("Spell"), Spell[].class);
        ArrayList<Placeable> cards = new ArrayList<>();
        cards.addAll(Arrays.asList(heroes));
        cards.addAll(Arrays.asList(minions));
        cards.addAll(Arrays.asList(items));
        cards.addAll(Arrays.asList(spells));
        if (ms != null) {
            cards.addAll(Arrays.asList(ms));
        }
        if (hs != null) {
            cards.addAll(Arrays.asList(hs));
        }
        this.cards = cards;
    }

    public Placeable getCard(String cardName) {
        for (Placeable card : cards) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }
    public ArrayList<Placeable> getCards() {
        return cards;
    }
}
