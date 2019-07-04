package models;

import com.google.gson.stream.JsonReader;
import models.Enums.ErrorType;
import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Shop {
    private static Shop shop = new Shop();
    private ArrayList<Placeable> cards;
    private Account account;

    private Shop() {
    }

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

    public static Shop getInstance() {
        return shop;
    }

    /**
     * sell() returns true when the selling is done completely and return false in the other case
     */
    public boolean sell(int cardID) {
        Placeable card = account.getCollection().getCard(cardID);
        if (card != null) {
            account.getCollection().deleteCardFromCollection(cardID);
            account.increaseMoney(card.getCost());
            return true;
        }
        return false;
    }

    /**
     * it's better to throw exceptions
     */
    public ErrorType buy(String cardName) {
        Placeable card = getCard(cardName);
        if (haveEnoughMoneyToBuyCard(card, account)) {
            if (card instanceof Item && !account.getCollection().canBuyItem()) {
                return ErrorType.MAX_ITEMS_IN_COLLECTION_REACHED;
            } else {
                account.getCollection().addCardToCollection(card);
                account.decreaseMoney(card.getCost());
                return ErrorType.NO_ERROR;
            }
        } else {
            return ErrorType.NOT_ENOUGH_MONEY_TO_BUY_CARD;
        }
    }

    public ArrayList<Placeable> getCards() {
        return cards;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Placeable getCard(String cardName) {
        for (Placeable card : cards) {
            if (card.getName().equals(cardName)) {
                return card;
            }
        }
        return null;
    }

    private boolean haveEnoughMoneyToBuyCard(Placeable card, Account account) {
        return account.getMoney() >= card.getCost();
    }

    public Account getAccount() {
        return account;
    }

}
