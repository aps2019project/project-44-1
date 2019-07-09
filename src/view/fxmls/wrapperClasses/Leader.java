package view.fxmls.wrapperClasses;

import models.Account;
import server.Environment;
import server.Request;
import server.RequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * a class that they made us to declare it; AGAINST OUR WISH :)))
 */
public class Leader {
    private String username;
    private int wins;
    private String online;

    private Leader(String username, int wins, String online) {
        this.username = username;
        this.wins = wins;
        this.online = online;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static ArrayList<Leader> accountToLeader(ArrayList<Account> accounts, HashMap<String, Account> onlineAccounts) {
        ArrayList<Leader> leaders = new ArrayList<>();
        for (Account a : accounts) {
            String username = a.getUsername();
            leaders.add(new Leader(username, a.getWins(), isOnline(onlineAccounts, username)));
        }
        return leaders;
    }

    private static String isOnline(HashMap<String, Account> onlineAccounts, String username) {
        for (Map.Entry<String, Account> o : onlineAccounts.entrySet()) {
            if (o.getValue().getUsername().equals(username))
                return "online";
        }
        return "offline";
    }

}
