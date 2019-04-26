package view;

import models.Account;
import models.ErrorType;
import models.Shop;

import java.util.ArrayList;

public class View {

    public void printError(ErrorType type) {
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

    public void printCollectionMenuHelp() {
    }       //TODO

    public void printCollectionItems() {

    }        //TODO

    public void printShopMenuHelp() {
    }       //TODO

    public void printShopItems(Shop shop) {
    }      //TODO

    public void sout(Object o){
        System.out.println(o);
    }
}