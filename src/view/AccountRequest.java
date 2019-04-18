package view;

import models.Account;

public class AccountRequest extends Request {

    private Account secondPlayer;

    public Account getSecondPlayer() {
        return secondPlayer;
    }

    public String getType() {
        return "hi";
    }
}
