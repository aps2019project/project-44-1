package models;

public class MatchHistory {
    String opponent;
    boolean hasWon;
    private long startDate;

    public MatchHistory(String opponent) {
        this.opponent = opponent;
        startDate = System.currentTimeMillis();
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

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
}
