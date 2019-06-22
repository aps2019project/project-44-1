package view.fxmls.wrapperClasses;

import models.MatchHistory;

import java.util.ArrayList;

public class History {
    private String opponent;
    private String w_l;
    private String time;

    public History(String opponent, String w_l, String time) {
        this.opponent = opponent;
        this.w_l = w_l;
        this.time = time;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getW_l() {
        return w_l;
    }

    public void setW_l(String w_l) {
        this.w_l = w_l;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static ArrayList<History> toHistory(ArrayList<MatchHistory> matchHistories) {
        ArrayList<History> histories = new ArrayList<>();
        for (MatchHistory mh : matchHistories) {
            histories.add(new History(mh.getOpponent(), mh.wl(), mh.toString()));
        }
        return histories;
    }

}
