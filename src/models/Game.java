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

    public static Account getAccount(String userName) {
        for (Account account : accounts) {
            if (account.getUsername().equals(userName)) {
                return account;
            }
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

//    public void login(String username, String password) {
//        account = Game.getAccount(username);
//        if (account == null)
//            view.printError(ErrorType.ACCOUNT_NOT_FOUND);
//        else if (!account.getPassword().equals(password))
//            view.printError(ErrorType.INVALID_PASSWORD);
//        Account.setMyAccount(account);
//        new AccountController().main();
//        account = null;
//    }

//    public void createAccount(String username, String password) {
//        if (duplicateUsername(username)) {
//            view.printError(ErrorType.USED_BEFORE_USERNAME);
//            return;
//        }
//        account.setUsername(username);
//        account.setPassword(password);
//        accounts.add(account);
//        Account.setMyAccount(account);
//        new AccountController().main();
//    }

    public void createAccount(String username, String password) {
        addAccount(new Account(username, password));
    }

    private void addAccount(Account account) {
        accounts.add(account);
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

    public boolean isUsedUsername(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidPassword(Account account, String password) {
        if (account.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
