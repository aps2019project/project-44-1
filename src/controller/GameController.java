package controller;

import models.Account;
import models.ErrorType;
import models.Game;
import view.GameRequest;
import view.GameRequestType;
import view.GameView;
import view.Request;

public class GameController extends Controller {
    private String command;
    private Game game = new Game();

    @Override
    public void main() {
        boolean isFinish = false;
        do {
            GameRequest request = new GameRequest();
            command = request.getCommand();
            switch (request.getGameRequestType()) {
                case LOGIN:
                    login();
                    break;
                case CREATE_ACCOUNT:
                    createAccount();
                    break;
                case LOGOUT:
                    logout();
                    break;
                case HELP:
                    help();
                    break;
                case SAVE:
                    save();
                    break;
                case SHOW_LEADERBOARD:
                    showLeaderboard();
                    break;
            }

        }
        while (!isFinish);
    }

    private void login() {
        String username = command.split("\\s")[1];
        Account account = Game.getAccount(username);
        if (account == null) {
            new GameView(ErrorType.INVALID_USERNAME);
            return;
        }
        GameRequest gameRequest = new GameRequest();
        gameRequest.setNewCommand();                    //TODO need print "enter your password" ?
        if (account.getPassword().equals(gameRequest.getCommand())) {
            AccountController accountController = new AccountController();
            accountController.main(account);
        } else new GameView(ErrorType.INVALID_PASSWORD);
    }

    private void createAccount() {
        String username = command.split("\\s")[2];
        if (duplicateUsername(username)) {
            new GameView(ErrorType.DUPLICATE_USERNAME);
            return;
        }
        Account account = new Account();
        account.setUsername(username);
        GameRequest gr = new GameRequest();
        gr.setNewCommand();
        if (gr.getCommand().matches("\\w+")) {
            account.setPassword(gr.getCommand());
            game.addAccount(account);
        } else new GameView(ErrorType.INVALID_PASSWORD);
    }

    private void showLeaderboard() {

    }

    private void help() {

    }

    private void save() {
    }

    private void logout() {
    }

    private boolean duplicateUsername(String username) {
        return Game.getAccount(username) == null;
    }
}
