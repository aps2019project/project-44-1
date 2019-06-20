package models;

import java.util.ArrayList;

/**
 * a class that they made us to declare it; AGAINST OUR WISH :)))
 */
public class Leader {
    private String username;
    private int wins;

    public Leader(String username, int wins) {
        this.username = username;
        this.wins = wins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public static ArrayList<Leader> accountToLeader(ArrayList<Account> accounts) {
        ArrayList<Leader> leaders = new ArrayList<>();
        for (Account a : accounts) {
            leaders.add(new Leader(a.getUsername(), a.getWins()));
        }
        return leaders;
    }

}
