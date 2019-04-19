package view;

import models.Account;
import models.ErrorType;

import java.util.ArrayList;

public class View {

    public void printError(ErrorType type) {        //after each error get new command
        if (type == null)
            return;
        System.out.println(type.getMessage());
    }

    public void printGetPasswordCommand() {
        System.out.println("Enter your password");
    }

    public void printLeaderboard(ArrayList<Account> accounts) {
        for (int i = 1; i <= accounts.size(); i++) {
            System.out.println(i + " - Username : " +
                    accounts.get(i - 1).getUsername() + " - Wins : " +
                    accounts.get(i - 1).getWins());
        }
    }

    public void printGameMenuHelp() {
    }       //TODO

    public void printAccountMenuHelp() {
    }        //TODO

    public void printColllectionMenuHelp() {
    }       //TODO

    public void printCollectionItems() {
    }        //TODO
}