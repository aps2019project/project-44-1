package view.fxmls.wrapperClasses;

import com.google.gson.*;
import models.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;

public class Serial implements JsonSerializer<Account> {

    @Override
    public JsonElement serialize(Account account, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        normalSerialize(account, jsonSerializationContext, object);
        JsonObject jsonObject = new JsonObject();
        collectionSerialize(account, jsonSerializationContext, object, jsonObject);
        return object;
    }

    private void collectionSerialize(Account account, JsonSerializationContext jsonSerializationContext, JsonObject object, JsonObject jsonObject) {
        HashSet<String> hashSet = new HashSet<>();
        for (Deck d : account.getCollection().getSortedDecks()) {
            ArrayList<String> ar = new ArrayList<>();
            for (Placeable c : d.getDeckCards()) {
                ar.add(c.getName());
                hashSet.add(c.getName());
            }
            jsonObject.add(d.getName(), jsonSerializationContext.serialize(ar));
        }
        jsonObject.add("collectionCards", jsonSerializationContext.serialize(hashSet));
        object.add("collection", jsonObject);
    }

    private void normalSerialize(Account account, JsonSerializationContext jsonSerializationContext, JsonObject object) {
        object.addProperty("username", account.getUsername());
        object.addProperty("money", account.getMoney());
        object.addProperty("wins", account.getWins());
        object.addProperty("password", account.getPassword());
        JsonArray array = new JsonArray();
        for (MatchHistory history : account.getHistories())
            array.add(jsonSerializationContext.serialize(history));
        object.add("histories", array);
    }

}
