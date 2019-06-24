package models;

public class MatchHistory {
    private String opponent;
    private boolean hasWon;
    private long startDate;

    public MatchHistory(String opponent, boolean hasWon) {
        this.opponent = opponent;
        startDate = System.currentTimeMillis();
        this.hasWon = hasWon;
    }

    private long getElapsedTimeInSec() {
        return (System.currentTimeMillis() - startDate) / 1000;
    }

    private String getElapsedTimeFormatted() {
        long elapsedTimeInMinute = getElapsedTimeInSec() / 60;
        if (elapsedTimeInMinute < 60) {
            return elapsedTimeInMinute + "minutes";
        } else {
            return elapsedTimeInMinute / 60 + "hours";
        }
    }

    @Override
    public String toString() {
        return getElapsedTimeFormatted() + "ago";
    }           //CHANGED!!!

    public String wl() {
        if (hasWon)
            return "WIN";
        else return "LOOSE";
    }

    public String getOpponent() {
        return opponent;
    }

}
