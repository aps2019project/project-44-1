package models;

import models.Enums.ErrorType;
import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Shop {
    private static Shop shop = new Shop();
    private ArrayList<String> cardNames = new ArrayList<>();
    private ArrayList<Placeable> cards = new ArrayList<>();
    private Account account;


    private Shop() {

    }

    public static Shop getInstance() {
        return shop;
    }


    public void help() {
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
     * it's better to throw execptions
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

    public void setCards(ArrayList<Placeable> cards) {

    }

    public void searchInShop(String cardName) {

    }

    public int searchInCollection(String cardName) {
        Collection collection = account.getCollection();
        return collection.searchInCollection(cardName);
    }

    @Override
    public String toString() {
        return "1.show collection\n" +
                "2.search [item name | card name]\n" +
                "3.search collection [item name | card name]\n" +
                "4.buy [card name | item name]\n" +
                "5.sell [card id | card id]\n" +
                "6.show\n" +
                "7.help\n" +
                "8.exit";
    }

    public ArrayList<Placeable> getCards() {
        return cards;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Placeable getCard(String cardName) {
        Gson gson = new Gson();
        File jsonFile = Paths.get("/Users/username/projects/workspace/foo/src/test/resources/file.json").toFile();
        JsonObject jsonObject = null;
        try {
            jsonObject = gson.fromJson(new FileReader(jsonFile), JsonObject.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonArray minionArray = jsonObject.getAsJsonArray("minion");
        for (JsonElement minion : minionArray) {
            if (minion.getAsJsonObject().get("name").getAsString().equals(cardName)){
                Card card = new Card();
                card.setName(minion.getAsJsonObject().get("name").getAsString());
                card.setHP(minion.getAsJsonObject().get("HP").getAsInt());
                card.setAP(minion.getAsJsonObject().get("AP").getAsInt());
                card.setRange(minion.getAsJsonObject().get("attackRange").getAsInt());
                card.setCost(minion.getAsJsonObject().get("cost").getAsInt());
                minion.getAsJsonObject().get("attackType").getAsString();
                minion.getAsJsonObject().get("neededMana").getAsInt();
                minion.getAsJsonObject().get("specialPowerCoolDown").getAsString();
                minion.getAsJsonObject().get("specialPower").getAsString();
                return card;
                }
            }
        return null;
    }

    public boolean haveEnoughMoneyToBuyCard(Placeable card, Account account) {
        return account.getMoney() >= card.getCost();
    }

    public boolean canBuyItem() {
        return account.getCollection().canBuyItem();
    }

}