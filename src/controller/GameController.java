package controller;

import models.Account;
import models.Enums.ErrorType;
import models.Game;
import view.GameRequest;
import view.View;

public class GameController {
    private Game game = new Game();
    private View view = new View();

    public void main() {
        GameRequest request;
        boolean isFinish = false;
        do {
            view.printStartMenu();/**show menu*/
            request = new GameRequest();
            request.getNewCommand();
            switch (request.getType()) {
                case LOGIN:
                    login(request);
                    break;
                case CREATE_ACCOUNT:
                    createAccount(request);
                    break;
                case HELP:
                    help();
                    break;
                case SHOW_LEADERBOARD:
                    showLeaderboard();
                    break;
                case EXIT:
                    isFinish = true;
                    break;
            }
        }
        while (!isFinish);
    }

    private void login(GameRequest request) {
        AccountController accountController = new AccountController();
        Account account = Game.getAccount(request.getUserName());
        String password = request.getPassword(view);
        if (account != null) {
            if (game.isValidPassword(account, password)) {
                accountController.main(account);
            } else {
                request.setError(ErrorType.INVALID_PASSWORD);
                view.printError(request.getError());
            }
        } else {
            request.setError(ErrorType.INVALID_USERNAME);
            view.printError(request.getError());
        }


    }

    private void createAccount(GameRequest request) {
        if (!game.isUsedUsername(request.getUserName())) {
            game.createAccount(request.getUserName(), request.getPassword(view));
        } else {
            request.setError(ErrorType.USED_BEFORE_USERNAME);
            view.printError(request.getError());
        }
    }

    private void showLeaderboard() {
        view.printLeaderboard(game.getSortedAccounts());
    }

    private void help() {
        view.printGameMenuHelp(game.toString());
    }
}
