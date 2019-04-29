package view;

import models.Account;
import models.ErrorType;
import models.Placeable;
import models.Shop;

import java.util.ArrayList;

public class View {

    public void printError(ErrorType type) {
        if (type == null)
            return;
        System.out.println(type.getMessage());
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

    public void printCollectionMenuHelp() {
    }       //TODO

    public void printCollectionItems(ArrayList<Placeable> list) {
    }

    public void printShopMenuHelp() {
    }       //TODO

    public void printShopItems(Shop shop) {
    }      //TODO

    public void sout(Object o) {
        System.out.println(o);
    }

    public void showMainMenu() {
        System.out.println("Main menu\n" +
                "-------\n" +
                "1.Collection\n" +
                "2.Shop\n" +
                "3.Battle\n" +
                "4.Exit\n" +
                "5.Help");
    }

    public void printGetPasswordCommand() {
        System.out.println("Enter your password:");
    }

    public void printStartMenu() {
        System.out.println("Start Menu\n" +
                "---------\n" +
                "1.login\n" +
                "2.create account\n" +
                "3.show leaderboard\n" +
                "4.help\n" +
                "5.exit");
    }
}