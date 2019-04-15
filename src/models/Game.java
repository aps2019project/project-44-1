package models;

import java.util.ArrayList;

public class Game {
    private static ArrayList<Account> accounts = new ArrayList<>();

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public static Account getAccount(String userName){
        for (Account a:accounts
             ) {
            if (userName.equals(a.getUsername()))
                return a;
        }
        return null;
    }

    public void showLeaderboard(){

    }
}
