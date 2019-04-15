package models;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Account> accounts = new ArrayList<>();

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    private void addAccount(Account account) {
        accounts.add(account);
    }

    public static Account getAccount(String userName) {
        return null;
    }

    public void showLeaderboard() {

    }

    public void createAccount(String username, String password) {
        addAccount(new Account(username, password));
    }

    public boolean isUsedUsername(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
