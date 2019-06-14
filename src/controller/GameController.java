package controller;

import models.Account;
import models.Enums.ErrorType;
import models.Game;

public class GameController extends Thread {
    private static GameController gameController = new GameController();
    private Game game = Game.getInstance();
    private String username;
    private String password;
    private boolean isLoginRequest;
    private String labelText = null;

    private GameController() {
    }

    public static GameController getInstance() {
        gameController.setName("gameController");
        gameController.setDaemon(true);
        return gameController;
    }

//    public void main() {
//        GameRequest request;
//        boolean isFinish = false;
//        do {
//            view.printStartMenu();          /*show menu*/
//            request = new GameRequest();
//            request.getNewCommand();
//            switch (request.getType()) {
//                case LOGIN:
//                    login(request);
//                    break;
//                case CREATE_ACCOUNT:
//                    createAccount(request);
//                    break;
//                case HELP:
//                    help();
//                    break;
//                case SHOW_LEADERBOARD:
//                    showLeaderboard();
//                    break;
//                case EXIT:
//                    isFinish = true;
//            }
//        }
//        while (!isFinish);
//    }

    private void login() {
        Account account = Game.getAccount(username);
        if (account != null) {
            if (game.isValidPassword(account, password)) {
                AccountController.getInstance().main(account);      // FIXME: 6/13/2019 here
            } else {
//                view.printError(ErrorType.INVALID_PASSWORD);
                labelText = ErrorType.INVALID_PASSWORD.getMessage();
            }
        } else {
//            view.printError(ErrorType.INVALID_USERNAME);
            labelText = ErrorType.INVALID_USERNAME.getMessage();
        }
    }

    private void createAccount() {
        if (!game.isUsedUsername(username)) {
            game.createAccount(username, password);
            labelText = ErrorType.ACCOUNT_CREATED.getMessage();
        } else {
//            view.printError(ErrorType.USED_BEFORE_USERNAME);
            labelText = ErrorType.USED_BEFORE_USERNAME.getMessage();
        }
    }
//
//    private void showLeaderboard() {
//        view.printLeaderboard(game.getSortedAccounts());
//    }
//
//    private void help() {
//        view.printGameMenuHelp(game.toString());
//    }

    @Override
    public void run() {
        System.out.println(gameController.getName());
        if (isLoginRequest)
            login();
        else
            createAccount();
    }

    public void getGraphicState(String username, String password, boolean isLoginRequest) {
        this.password = password;
        this.username = username;
        this.isLoginRequest = isLoginRequest;
    }

    public String getLabelText() {
        return labelText;
    }

}