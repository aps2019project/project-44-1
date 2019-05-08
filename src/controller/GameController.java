package controller;

import models.Account;
import models.Enums.ErrorType;
import models.Game;
import view.GameRequest;
import view.View;

public class GameController {
    private static GameController gameController = new GameController();
    private Game game = Game.getInstance();
    private View view = View.getInstance();

    private GameController() {
    }

    public static GameController getInstance() {
        return gameController;
    }

    public void main() {
        GameRequest request;
        boolean isFinish = false;
        do {
            view.printStartMenu();          /*show menu*/
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
        Account account = Game.getAccount(request.getUserName());
        String password = request.getPassword(view);
        if (account != null) {
            if (game.isValidPassword(account, password)) {
                AccountController.getInstance().main(account);
            } else {
                view.printError(ErrorType.INVALID_PASSWORD);
            }
        } else {
            view.printError(ErrorType.INVALID_USERNAME);
        }
    }

    private void createAccount(GameRequest request) {
        if (!game.isUsedUsername(request.getUserName())) {
            game.createAccount(request.getUserName(), request.getPassword(view));
        } else {
            view.printError(ErrorType.USED_BEFORE_USERNAME);
        }
    }

    private void showLeaderboard() {
        view.printLeaderboard(game.getSortedAccounts());
    }

    private void help() {
        view.printGameMenuHelp(game.toString());
    }

}