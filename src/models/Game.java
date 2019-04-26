package models;

import controller.AccountController;
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

    private static Account getAccount(String userName) {
        for (Account a : accounts) {
            if (userName.equals(a.getUsername()))
                return a;
        }
        return null;
    }

    private boolean duplicateUsername(String username) {
        for (Account account : getAccounts()) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void login(String username, String password) {
        account = Game.getAccount(username);
        if (account == null)
            view.printError(ErrorType.ACCOUNT_NOT_FOUND);
        else if (!account.getPassword().equals(password))
            view.printError(ErrorType.INVALID_PASSWORD);
        Account.setMyAccount(account);
        new AccountController().main();
        account = null;
    }

    public void createAccount(String username, String password) {
        if (duplicateUsername(username)) {
            view.printError(ErrorType.USED_BEFORE_USERNAME);
            return;
        }
        account.setUsername(username);
        account.setPassword(password);
        accounts.add(account);
        Account.setMyAccount(account);
        new AccountController().main();
    }

    public void showLeaderboard() {
        Collections.sort(accounts);
        view.printLeaderboard(accounts);
    }

    /**
     * a good way to create unique IDs
     */
    public static int IDCreator() {
        ID++;
        return ID;
    }
}
