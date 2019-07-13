package models;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import server.Database;
import server.Request;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Game {

    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Game game = new Game();
    private HashMap<String, Account> onlineAccounts = new HashMap<>();
    private LinkedList<Request> requestedForBattle = new LinkedList<>();

    private Game() {
        fillAccounts();
    }

    public static Game getInstance() {
        return game;
    }

    public static ArrayList<Account> getAccounts() {
        return accounts;
    }

    public static Account getAccount(String userName) {
        for (Account account : accounts) {
            if (account.getUsername().equals(userName)) {
                return account;
            }
        }
        return null;
    }

    public void createAccount(String username, String password) {
        Account account = new Account(username, password);
        accounts.add(account);
        Database.putElement("Accounts", username, account);

    }

    public ArrayList<Account> getSortedAccounts() {
        Collections.sort(accounts);
        return accounts;
    }

    public boolean isUsedUsername(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidPassword(Account account, String password) {
        return account.getPassword().equals(password);
    }

    /**
     * global and useful method to change scenes
     *
     * @since adding graphics to game
     */
    public void loadPage(Node node, final String url) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        try {
            node.getScene().setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillAccounts() {
        Gson gson = new Gson();
        JsonReader reader;
        try {
            reader = new JsonReader(new FileReader("src\\models\\accountSaves.json"));
            Account[] json = gson.fromJson(reader, Account[].class);
            if (json != null)
                accounts.addAll(Arrays.asList(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Account> getOnlineAccounts() {
        return onlineAccounts;
    }

    public boolean isOnline(String username) {
        return onlineAccounts.containsKey(username);
    }

    public void addToOnlineAccounts(Account account) {
        onlineAccounts.put(account.getUsername(), account);
    }

    public void removeFromOnlineAccounts(Account account) {
        onlineAccounts.remove(account.getUsername());
    }

    public void removeFromOnlineAccounts(String username) {
        onlineAccounts.remove(username);
    }

    public LinkedList<Request> getRequestedForBattle() {
        return requestedForBattle;
    }

    static {
        Account test = new Account("test", "1234");
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("divsefid"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("simorgh"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("ejhdeha"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("KamandarFars"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("neyzedarfars"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("tajdanaei"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("Madness"));
        Game.getAccounts().add(test);
        try {
            test.getCollection().createDeck("test");
        } catch (Exceptions.DuplicateNameForDeck duplicateNameForDeck) {
            duplicateNameForDeck.printStackTrace();
        }
        test.getCollection().addToDeck(1, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().selectMainDeck("test");


    }

    static {
        Account test = new Account("test2", "1234");
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("simorgh"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("simorgh"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("ejhdeha"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("KamandarFars"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("neyzedarfars"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("tajdanaei"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("Madness"));
        Game.getAccounts().add(test);
        try {
            test.getCollection().createDeck("test");
        } catch (Exceptions.DuplicateNameForDeck duplicateNameForDeck) {
            duplicateNameForDeck.printStackTrace();
        }
        test.getCollection().addToDeck(1, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(4, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().addToDeck(7, "test");
        test.getCollection().selectMainDeck("test");


    }

}
