package models;

public class MatchHistory {
    String opponent;
    boolean hasWon;
    private long startDate;

    public MatchHistory(String opponent, boolean hasWon) {
        this.opponent = opponent;
        startDate = System.currentTimeMillis();
        this.hasWon = hasWon;
    }

    private long getElapsedTimeInSec() {
        return (System.currentTimeMillis() - startDate) / 1000;
    }


    public String getElapsedTimeFormatted() {
        long elapsedTimeInMinute = getElapsedTimeInSec() / 60;
        if (elapsedTimeInMinute < 60) {
            return Long.toString(elapsedTimeInMinute);
        } else {
            return Long.toString(elapsedTimeInMinute / 60);
        }
    }
}
