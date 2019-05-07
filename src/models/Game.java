package models;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private static ArrayList<Account> accounts = new ArrayList<>();
    //    private Account account1;
//    private Account account2;
//    private static int ID = 0;
    private static Game game = new Game();

    private Game() {
    }

//    public Account getAccount1() {
//        return account1;
//    }
//
//    public Account getAccount2() {
//        return account2;
//    }

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
        addAccount(new Account(username, password));
    }
//
//    public void setAccount1(Account account1) {
//        this.account1 = account1;
//    }

//    public void setAccount2(Account account2) {
//        this.account2 = account2;
//    }

    private void addAccount(Account account) {
        accounts.add(account);
    }

    public ArrayList<Account> getSortedAccounts() {
        Collections.sort(accounts);
        return accounts;
    }

    /**
     * a good way to create unique IDs
     */
//    public static int IDCreator() {
//        ID++;
//        return ID;
//    }
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

    @Override
    public String toString() {
        return "1.login\n" +
                "2.creat account\n" +
                "3.show leaderboard\n" +
                "4.Help\n" +
                "5.Exit";
    }

    {
        Account test = new Account("test","1234");
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("divsefid"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("simorgh"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("ejhdeha"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("KamandarFars"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("neyzedarfars"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("tajdanaei"));
        test.getCollection().addCardToCollection(Shop.getInstance().getCard("Madness"));
        Game.getAccounts().add(test);
        test.getCollection().createDeck("test");
        test.getCollection().addToDeck(1,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");
        test.getCollection().addToDeck(7,"test");


    }

}