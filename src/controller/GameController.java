package controller;

import models.Account;
import models.ErrorType;
import models.Game;
import view.GameRequest;
import view.GameRequestType;
import view.GameView;
import view.Request;

public class GameController extends Controller {
    private GameView view = new GameView();

    @Override
    public void main() {
        boolean isFinish = false;
        do {
            GameRequest request = new GameRequest();
            switch (request.getGameRequestType()) {
                case LOGIN:
                    login(view, request);
                    break;
                case CREATE_ACCOUNT:
                    createAccount(request.getCommand());
                    break;
                case LOGOUT:
                    logout();
                    break;
                case HELP:
                    help(view);
                    break;
                case SAVE:
                    save();
                    break;
                case SHOW_LEADERBOARD:
                    showLeaderboard(view);
                    break;
            }

        }
        while (!isFinish);
    }

    private void login(GameView view, GameRequest request) {
        AccountController accountController = new AccountController();
        accountController.main(Game.getAccount("username"));
    }

    private void createAccount(String command) {
        String username = command.split("\\s")[2];
        if (duplicateUsername(username)) {
            view.print(ErrorType.DUPLICATE_USERNAME);
            return;
        }
        Account account = new Account();
        account.setUsername(username);
        GameRequest gr = new GameRequest();
        gr.setNewCommand();
        if (gr.getCommand().matches("\\w+"))
            account.setPassword(gr.getCommand());
        else view.print(ErrorType.INVALID_PASSWORD);
    }

    private void showLeaderboard(GameView view) {

    }

    private void help(GameView view) {

    }

    private void save() {
    }

    private void logout() {
    }

    private boolean duplicateUsername(String username) {
        for (Account account : Account.getAccounts()) {
            if (account.getUsername().equals(username))
                return true;
        }
        return false;
    }
}
