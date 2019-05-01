package models;

import view.View;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private View view = new View();
    private Account account;
    private static int ID = 0;


    private ArrayList<Account> getAccounts() {
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
    public static int IDCreator() {
        ID++;
        return ID;
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

    @Override
    public String toString() {
        return "1.login\n" +
                "2.creat account\n" +
                "3.show leaderboard\n" +
                "4.help\n" +
                "5.exit";
    }
}
