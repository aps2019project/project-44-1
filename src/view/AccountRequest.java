package view;

import models.Account;

public class AccountRequest extends Request {
    GameRequestType accountRequestType;

    AccountRequest accountRequest = new AccountRequest();
    Account secondPlayer;

    public Account getSecondPlayer() {
        return secondPlayer;
    }

    public String getType() {
        return "hi";
    }
}
