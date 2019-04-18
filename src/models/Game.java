package models;

import controller.AccountController;
import view.GameRequest;
import view.View;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private View view = new View();
    private Account account = new Account();

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
        new AccountController().main(account);
    }

    public void createAccount(String username, String password) {
        if (duplicateUsername(username)) {
            view.printError(ErrorType.USED_BEFORE_USERNAME);
            return;
        }
        account.setUsername(username);
        account.setPassword(password);
        accounts.add(account);
    }

    public void showLeaderboard() {
        Collections.sort(accounts);
        view.printLeaderboard(accounts);
    }

    public void help() {    //TODO here or in View class
    }

}
